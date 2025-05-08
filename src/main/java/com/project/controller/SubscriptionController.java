package com.project.controller;

import com.project.dto.SubscriptionAddRequest;
import com.project.dto.SubscriptionResponse;
import com.project.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{id}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subService;

    public SubscriptionController(SubscriptionService subService) {
        this.subService = subService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> addSubscription(
            @PathVariable("id") Integer userId,
            @RequestBody SubscriptionAddRequest dto) {
        return ResponseEntity.ok(subService.addSubscription(userId,dto));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptions(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(subService.getSubscriptions(userId));
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("id") Integer userId, @PathVariable("subId") Integer subId) {
        subService.deleteSubscriptions(userId, subId);
        return ResponseEntity.noContent().build();
    }
}
