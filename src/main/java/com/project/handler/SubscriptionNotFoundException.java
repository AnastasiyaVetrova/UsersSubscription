package com.project.handler;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Integer userId, Integer subId) {
        super(String.format("Подписки с id = %d и пользователем id = %d не существует", subId, userId));
    }
}
