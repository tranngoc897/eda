package com.example.match.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Season {

    private String id;
    private String name;

    private Season() {
    }

    Season(String id, String name) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Match scheduleMatch(String matchId, LocalDateTime date, String homeClubId, String awayClubId) {
        return new Match(matchId, id, date, new Team(homeClubId), new Team(awayClubId));
    }

    public Player startCareer(String playerId, String name) {
        return new Player(playerId, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
