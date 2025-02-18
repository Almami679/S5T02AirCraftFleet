package com.microservice.battle.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BattlePlayerDTO {
    private Long id;
    private String username;
    private PlaneDTO plane;
    private int score;
}
