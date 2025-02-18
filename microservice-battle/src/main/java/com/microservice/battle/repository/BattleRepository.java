package com.microservice.battle.repository;

import com.microservice.battle.entities.Battle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends MongoRepository<Battle, Long> {
}
