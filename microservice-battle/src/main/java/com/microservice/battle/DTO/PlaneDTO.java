package com.microservice.battle.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaneDTO {
    private Long id;
    private String name;
    private String model;
    private int health;
    private int attack;
}

