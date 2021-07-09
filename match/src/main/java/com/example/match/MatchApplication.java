package com.example.match;

import java.util.Properties;

import com.example.match.domain.Match;
import com.example.match.domain.Player;
import com.example.match.repo.SeasonRepository;
import com.example.match.snapshot.DomainUpdater;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.ibss.common.model.event.Event;
import vn.ibss.common.stream.EventPublisher;
import vn.ibss.common.stream.JsonPojoSerde;
import vn.ibss.common.stream.KafkaStreamsStarter;
import vn.ibss.common.stream.StateStoreRepository;
import vn.ibss.common.util.MicroserviceUtils;


@SpringBootApplication
public class MatchApplication {

    private static final Logger logger = LoggerFactory.getLogger(MatchApplication.class);

    private static final String APP_ID = MicroserviceUtils.applicationId(MatchApplication.class);

    @Value("${kafka.bootstrapAddress}")
    private String kafkaBootstrapAddress;

    @Value("${apiVersion}")
    private int apiVersion;

    @Value("${kafkaTimeout:60000}")
    private long kafkaTimeout;

    @Value("${streamsStartupTimeout:20000}")
    private long streamsStartupTimeout;

    @Bean
    public KafkaStreams kafkaStreams() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        DomainUpdater snapshotBuilder = new DomainUpdater(leagueRepository());
        Topology topology = streamsBuilder.build();
        snapshotBuilder.init(topology);
        KafkaStreamsStarter starter = new KafkaStreamsStarter(kafkaBootstrapAddress, topology, APP_ID);
        starter.setKafkaTimeout(kafkaTimeout);
        starter.setStreamsStartupTimeout(streamsStartupTimeout);
        return starter.start();
    }

    @Bean
    public EventPublisher eventPublisher() {
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapAddress);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPojoSerde.class.getName());
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, APP_ID);
        var kafkaProducer = new KafkaProducer<String, Event>(producerProps);
        return new EventPublisher(kafkaProducer, APP_ID, apiVersion);
    }

    @Bean
    public SeasonRepository leagueRepository() {
        return new SeasonRepository();
    }

    @Bean
    public StateStoreRepository<Match> matchRepository() {
        return new StateStoreRepository<>(kafkaStreams(), DomainUpdater.MATCH_STORE);
    }

    @Bean
    public StateStoreRepository<Player> playerRepository() {
        return new StateStoreRepository<>(kafkaStreams(), DomainUpdater.PLAYER_STORE);
    }

    public static void main(String[] args) {
        logger.info("Application ID: {}", APP_ID);
        SpringApplication.run(MatchApplication.class, args);
    }
}
