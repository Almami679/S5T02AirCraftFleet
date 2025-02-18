package com.microservice.battle.BattleService;

import com.microservice.battle.DTO.BattlePlayerDTO;
import com.microservice.battle.DTO.PlaneDTO;
import com.microservice.battle.entities.Battle;

public interface BattleServiceInterface {
    Long getNextId();
    Battle addBattle(Long userId1, Long userId2, Long planeId1, Long planeId2);
    Battle getBattle(Long id);
    Battle executeBattle(Battle battle);
    BattlePlayerDTO determinateWinner(BattlePlayerDTO player1, BattlePlayerDTO player2);
    double calculateWinProbability(PlaneDTO plane1, PlaneDTO plane2);
    void defineScore(BattlePlayerDTO player1, BattlePlayerDTO player2, BattlePlayerDTO winner);

}
