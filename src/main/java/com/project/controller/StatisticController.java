package com.project.controller;

import com.project.model.enums.SubscriptionType;
import com.project.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    private final SubscriptionService subService;

    public StatisticController(SubscriptionService subService) {
        this.subService = subService;
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<SubscriptionType>> getTopSubscriptions(
            @RequestParam(name = "limit", required = false, defaultValue = "3") Integer limit) {
        return ResponseEntity.ok(subService.getTopSubscriptions(limit));
    }
}
