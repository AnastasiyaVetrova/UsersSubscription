package com.project.dto;

import com.project.model.enums.SubscriptionType;

public record SubscriptionAddRequest(
        SubscriptionType subscription) {
}
