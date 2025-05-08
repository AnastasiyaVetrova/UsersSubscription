package com.project.handler;

import com.project.model.enums.SubscriptionType;

public class DuplicateSubscriptionException extends RuntimeException {
    public DuplicateSubscriptionException(Integer userId, SubscriptionType sub) {
        super(String.format("Пользователь с id = %d уже имеет подписку на %s", userId, sub));
    }
}
