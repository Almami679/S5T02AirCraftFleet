package com.microservice.battle.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.microservice.battle.DTO.BattlePlayerDTO;

@FeignClient(name = "msvc-users", url = "http://localhost:8081/users")
public interface UserClient {

    @GetMapping("/{userId}")
    BattlePlayerDTO getUserById(@PathVariable Long userId);

    @PutMapping("/{userId}/score")
    void updateUserScore(@PathVariable Long userId, @RequestParam int newScore);
}

