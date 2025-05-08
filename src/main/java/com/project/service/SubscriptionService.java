package com.project.service;

import com.project.dto.SubscriptionAddRequest;
import com.project.dto.SubscriptionResponse;
import com.project.model.enums.SubscriptionType;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponse addSubscription(Integer id, SubscriptionAddRequest dto);

    List<SubscriptionResponse> getSubscriptions(Integer id);

    void deleteSubscriptions(Integer id, Integer subId);

    List<SubscriptionType> getTopSubscriptions(Integer limit);
}
