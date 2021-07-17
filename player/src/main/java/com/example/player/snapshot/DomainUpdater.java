package com.example.player.snapshot;

import com.example.player.domain.Player;
import org.apache.kafka.streams.Topology;
import vn.ibss.common.model.event.PlayerStartedCareer;
import static vn.ibss.common.stream.StreamsUtils.addProcessor;
import static vn.ibss.common.stream.StreamsUtils.addStore;


public class DomainUpdater {

    public static final String PLAYER_STORE = "player_store";

    public void init(Topology topology) {

        addProcessor(topology, PlayerStartedCareer.class, (eventId, event, store) -> {

            Player player = new Player(event.getPlayerId(), event.getName());
            store.put(player.getId(), player);

        }, PLAYER_STORE);

        addStore(topology, Player.class, PLAYER_STORE, PlayerStartedCareer.class);
    }
}
