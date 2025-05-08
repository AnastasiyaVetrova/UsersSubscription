package com.project.dto;

import com.project.model.enums.SubscriptionType;

import java.time.LocalDateTime;

public record SubscriptionResponse(
        Integer id,
        Integer userId,
        SubscriptionType subscription,
        LocalDateTime subscribedAt) {
}
