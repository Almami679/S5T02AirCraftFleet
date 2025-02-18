package com.microservice.battle.exceptions;

public class BattleNotFoundException extends RuntimeException {
    public BattleNotFoundException(String message) {
        super(message);
    }
}
