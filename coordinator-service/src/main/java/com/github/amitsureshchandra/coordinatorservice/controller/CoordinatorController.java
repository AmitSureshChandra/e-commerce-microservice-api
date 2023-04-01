package com.github.amitsureshchandra.coordinatorservice.controller;

import com.github.amitsureshchandra.coordinatorservice.service.CoodinatorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinators")
public class CoordinatorController {

    final CoodinatorService coodinatorService;

    public CoordinatorController(CoodinatorService coodinatorService) {
        this.coodinatorService = coodinatorService;
    }


}
