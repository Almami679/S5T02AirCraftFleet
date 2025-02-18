package com.microservice.battle.client;


import com.microservice.battle.DTO.PlaneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-planes", url = "http://localhost:8091/planes")
public interface PlaneClient {

    @GetMapping("/{planeId}")
    PlaneDTO getPlaneById(@PathVariable Long planeId);
}
