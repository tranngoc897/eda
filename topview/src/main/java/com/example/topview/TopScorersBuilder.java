package com.example.topview;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Serialized;
import vn.ibss.common.model.view.PlayerGoals;
import vn.ibss.common.model.view.TopPlayers;
import vn.ibss.common.stream.JsonPojoSerde;
import vn.ibss.common.util.Topics;

import static org.apache.kafka.common.serialization.Serdes.String;
import static vn.ibss.common.stream.StreamsUtils.materialized;
/**
 * Builder that creates Kafka Streams topology for creating top scorers ranking.
 */
public class TopScorersBuilder {

    public static final String TOP_SCORERS_STORE = "top_scorers_store";

    public static final String PLAYER_GOALS_TOPIC = Topics.viewTopicName(PlayerGoals.class);
    public static final String TOP_SCORERS_TOPIC = Topics.viewTopicName(TopPlayers.class);

    private final JsonPojoSerde<PlayerGoals> playerGoalsSerde = new JsonPojoSerde<>(PlayerGoals.class);
    private final JsonPojoSerde<TopPlayers> topSerde = new JsonPojoSerde<>(TopPlayers.class);

    private final StreamsBuilder builder;

    public TopScorersBuilder(StreamsBuilder builder) {
        this.builder = builder;
    }

    public void build() {
        KTable<String, TopPlayers> top10Table = builder
                .stream(PLAYER_GOALS_TOPIC, Consumed.with(Serdes.String(), playerGoalsSerde))
                // create a single record that includes the top scorers
                .groupBy((playerId, playerGoals) -> "topPlayers", Serialized.with(Serdes.String(), playerGoalsSerde))
                .aggregate(() -> new TopPlayers(10), (playerId, playerStat, top10) -> top10.aggregate(playerStat),
                    materialized(TOP_SCORERS_STORE, topSerde));

        top10Table.toStream().to(TOP_SCORERS_TOPIC, Produced.with(String(), topSerde));
    }
}
