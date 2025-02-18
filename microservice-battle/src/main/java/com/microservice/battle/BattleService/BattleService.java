package com.microservice.battle.BattleService;

import com.microservice.battle.client.PlaneClient;
import com.microservice.battle.client.UserClient;
import com.microservice.battle.DTO.BattlePlayerDTO;
import com.microservice.battle.DTO.PlaneDTO;
import com.microservice.battle.entities.Battle;
import com.microservice.battle.exceptions.BattleNotFoundException;
import com.microservice.battle.repository.BattleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {

    private static final Logger logger = LoggerFactory.getLogger(BattleService.class);

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PlaneClient planeClient;

    public List<Battle> getAllBattles() {
        logger.info("Fetching all battles");
        return battleRepository.findAll();
    }

    public Battle getBattle(Long id) {
        logger.info("Fetching battle with ID: {}", id);
        return battleRepository.findById(id)
                .orElseThrow(() -> new BattleNotFoundException("Battle not found with id " + id));
    }

    public Battle addBattle(Long userId1, Long userId2, Long planeId1, Long planeId2) {
        logger.info("Creating a new battle between user {} and user {}", userId1, userId2);
        BattlePlayerDTO player1 = userClient.getUserById(userId1);
        BattlePlayerDTO player2 = userClient.getUserById(userId2);

        PlaneDTO plane1 = planeClient.getPlaneById(planeId1);
        PlaneDTO plane2 = planeClient.getPlaneById(planeId2);

        player1.setPlane(plane1);
        player2.setPlane(plane2);

        Battle battle = new Battle(player1, player2);
        return battleRepository.save(battle);
    }

    public Battle executeBattle(Battle battle) {
        logger.info("Executing battle between {} and {}", battle.getUser1().getUsername(), battle.getUser2().getUsername());

        BattlePlayerDTO winner = determinateWinner(battle.getUser1(), battle.getUser2());
        battle.setWinner(winner);

        defineScore(battle.getUser1(), battle.getUser2(), winner);

        return battleRepository.save(battle);
    }

    private BattlePlayerDTO determinateWinner(BattlePlayerDTO player1, BattlePlayerDTO player2) {
        double probWinPlayer1 = calculateWinProbability(player1.getPlane(), player2.getPlane());
        return (Math.random() < probWinPlayer1) ? player1 : player2;
    }

    private double calculateWinProbability(PlaneDTO plane1, PlaneDTO plane2) {
        double score1 = plane1.getAttack() * 0.6 + plane1.getHealth() * 0.4;
        double score2 = plane2.getAttack() * 0.6 + plane2.getHealth() * 0.4;
        return score1 / (score1 + score2);
    }

    private void defineScore(BattlePlayerDTO player1, BattlePlayerDTO player2, BattlePlayerDTO winner) {
        int winnerPoints = 130;
        int loserPoints = 60;

        if (player1.equals(winner)) {
            player1.setScore(player1.getScore() + winnerPoints);
            player2.setScore(player2.getScore() + loserPoints);
        } else {
            player2.setScore(player2.getScore() + winnerPoints);
            player1.setScore(player1.getScore() + loserPoints);
        }

        userClient.updateUserScore(player1.getId(), player1.getScore());
        userClient.updateUserScore(player2.getId(), player2.getScore());
    }
}
