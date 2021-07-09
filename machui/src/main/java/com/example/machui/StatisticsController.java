package com.example.machui;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import vn.ibss.common.model.view.MatchScore;
import vn.ibss.common.model.view.PlayerGoals;
import vn.ibss.common.model.view.TeamRanking;
import vn.ibss.common.stream.StateStoreRepository;

@RestController
@RequestMapping(path = "/ui", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsController<PlayerCards> {

    private final StateStoreRepository<MatchScore> matchScoreRepo;
    private final StateStoreRepository<TeamRanking> teamRankingRepo;
    private final StateStoreRepository<PlayerCards> playerCardsRepo;
    private final StateStoreRepository<PlayerGoals> playerGoalsRepo;

    public StatisticsController(StateStoreRepository<MatchScore> matchScoreRepo,
            StateStoreRepository<TeamRanking> teamRankingRepo,
            StateStoreRepository<PlayerGoals> playerGoalsRepo,
            StateStoreRepository<PlayerCards> playerCardsRepo) {
        this.matchScoreRepo = matchScoreRepo;
        this.teamRankingRepo = teamRankingRepo;
        this.playerGoalsRepo = playerGoalsRepo;
        this.playerCardsRepo = playerCardsRepo;
    }

    @GetMapping("/matchScores")
    public Flux<MatchScore> getMatchScores() {
        return matchScoreRepo.findAll();
    }

    @GetMapping("/rankings")
    public Flux<TeamRanking> getRankings() {
        return teamRankingRepo.findAll();
    }

    @GetMapping("/goals")
    public Flux<PlayerGoals> getGoals() {
        return playerGoalsRepo.findAll();
    }

    @GetMapping("/cards")
    public Flux<PlayerCards> getCards() {
        return playerCardsRepo.findAll();
    }
}
