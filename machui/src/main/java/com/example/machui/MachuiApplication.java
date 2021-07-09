package com.example.machui;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import vn.ibss.common.model.view.MatchScore;
import vn.ibss.common.model.view.PlayerCards;
import vn.ibss.common.model.view.PlayerGoals;
import vn.ibss.common.model.view.TeamRanking;
import vn.ibss.common.stream.KafkaStreamsStarter;
import vn.ibss.common.stream.StateStoreRepository;
import vn.ibss.common.util.MicroserviceUtils;

@SpringBootApplication
public class MachuiApplication {


    private static final Logger logger = LoggerFactory.getLogger(MachuiApplication.class);

    private static final String APP_ID = MicroserviceUtils.applicationId(MachuiApplication.class);

    @Value("${kafka.bootstrapAddress}")
    private String kafkaBootstrapAddress;

    @Value("${kafkaTimeout:60000}")
    private long kafkaTimeout;

    @Value("${streamsStartupTimeout:20000}")
    private long streamsStartupTimeout;

    @Autowired
    private SimpMessagingTemplate stomp;

    @Bean
    public KafkaStreams kafkaStreams() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        StatisticsKeeper statisticsBuilder = new StatisticsKeeper(streamsBuilder, stomp);
        statisticsBuilder.build();
        
        Topology topology = streamsBuilder.build();
        
        KafkaStreamsStarter starter = new KafkaStreamsStarter(kafkaBootstrapAddress, topology, APP_ID);
        starter.setKafkaTimeout(kafkaTimeout);
        starter.setStreamsStartupTimeout(streamsStartupTimeout);
        return starter.start();
    }

    @Bean
    public StateStoreRepository<MatchScore> matchScoresRepo() {
        return new StateStoreRepository<>(kafkaStreams(), StatisticsKeeper.MATCH_SCORES_STORE);
    }

    @Bean
    public StateStoreRepository<TeamRanking> teamRankingRepo() {
        return new StateStoreRepository<>(kafkaStreams(), StatisticsKeeper.TEAM_RANKING_STORE);
    }

    @Bean
    public StateStoreRepository<PlayerGoals> playerGoalsRepo() {
        return new StateStoreRepository<>(kafkaStreams(), StatisticsKeeper.PLAYER_GOALS_STORE);
    }

    @Bean
    public StateStoreRepository<PlayerCards> playerCardsRepo() {
        return new StateStoreRepository<>(kafkaStreams(), StatisticsKeeper.PLAYER_CARDS_STORE);
    }

    public static void main(String[] args) {
        logger.info("Application ID: {}", APP_ID);
        SpringApplication.run(MachuiApplication.class, args);
    }
}
