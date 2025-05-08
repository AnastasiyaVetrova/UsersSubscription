package com.project.service.impl;

import com.project.dto.SubscriptionAddRequest;
import com.project.dto.SubscriptionResponse;
import com.project.handler.DuplicateSubscriptionException;
import com.project.handler.SubscriptionNotFoundException;
import com.project.handler.UserNotFoundException;
import com.project.model.Subscription;
import com.project.model.User;
import com.project.model.enums.SubscriptionType;
import com.project.model.mapper.SubscriptionMapper;
import com.project.repository.SubscriptionRepository;
import com.project.repository.UserRepository;
import com.project.service.SubscriptionService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subRepository;
    private final SubscriptionMapper subMapper;

    public SubscriptionServiceImpl(UserRepository userRepository, SubscriptionRepository subRepository, SubscriptionMapper subMapper) {
        this.userRepository = userRepository;
        this.subRepository = subRepository;
        this.subMapper = subMapper;
    }

    @Override
    @Transactional
    public SubscriptionResponse addSubscription(Integer userId, SubscriptionAddRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Subscription sub = subMapper.toEntity(dto);

        if (subRepository.existsByUser_IdAndSubscription(userId, sub.getSubscription())) {
            throw new DuplicateSubscriptionException(user.getId(), dto.subscription());
        }

        sub.setUser(user);

        return subMapper.toDto(subRepository.save(sub));
    }

    @Override
    public List<SubscriptionResponse> getSubscriptions(Integer id) {
        return subRepository.findByUserId(id).stream().map(subMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void deleteSubscriptions(Integer userId, Integer subId) {
        if (!subRepository.existsByUser_IdAndId(userId, subId)) {
            throw new SubscriptionNotFoundException(userId, subId);
        }
        subRepository.deleteById(subId);
    }

    @Override
    public List<SubscriptionType> getTopSubscriptions(Integer limit) {
        return subRepository.findTopSubscriptions(PageRequest.of(0,limit));
    }
}
