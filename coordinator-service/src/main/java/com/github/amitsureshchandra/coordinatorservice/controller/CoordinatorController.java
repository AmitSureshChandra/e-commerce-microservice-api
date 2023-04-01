package com.github.amitsureshchandra.coordinatorservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coordinators")
public class CoordinatorController {

//    final CoodinatorService coodinatorService;

//    public CoordinatorController(CoodinatorService coodinatorService) {
//        this.coodinatorService = coodinatorService;
//    }

//    @PostMapping("/coordinate/{tx_id}")
//    public void coordinate(@PathVariable UUID tx_id) {
//        coodinatorService.coordinate(tx_id);
//    }


}
