package com.microservice.battle.battleController;

import com.microservice.battle.BattleService.BattleService;
import com.microservice.battle.entities.Battle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @Operation(summary = "Get all battles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Battles retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Battle.class)))
    })
    @GetMapping
    public ResponseEntity<List<Battle>> getAllBattles() {
        return ResponseEntity.ok(battleService.getAllBattles());
    }

    @Operation(summary = "Get a battle by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Battle found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Battle.class))),
            @ApiResponse(responseCode = "404", description = "Battle not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Battle> getBattleById(@PathVariable Long id) {
        return ResponseEntity.ok(battleService.getBattle(id));
    }

    @Operation(summary = "Create a new battle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Battle created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Battle.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    public ResponseEntity<Battle> createBattle(@RequestParam Long userId1, @RequestParam Long userId2,
                                               @RequestParam Long planeId1, @RequestParam Long planeId2) {
        return ResponseEntity.ok(battleService.addBattle(userId1, userId2, planeId1, planeId2));
    }

    @Operation(summary = "Execute a battle and determine the winner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Battle executed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Battle.class))),
            @ApiResponse(responseCode = "404", description = "Battle not found")
    })
    @PostMapping("/execute/{id}")
    public ResponseEntity<Battle> executeBattle(@PathVariable Long id) {
        return ResponseEntity.ok(battleService.executeBattle(battleService.getBattle(id)));
    }
}

