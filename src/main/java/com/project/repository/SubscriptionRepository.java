package com.project.repository;

import com.project.model.Subscription;
import com.project.model.enums.SubscriptionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    boolean existsByUser_IdAndSubscription(Integer id, SubscriptionType subscription);

    boolean existsByUser_IdAndId(Integer id, Integer subId);

    List<Subscription> findByUserId(Integer userId);

    @Query("""
            SELECT s.subscription
            FROM Subscription s
            GROUP BY s.subscription
            ORDER BY COUNT(s) DESC
            """)
    List<SubscriptionType> findTopSubscriptions(Pageable pageable);
}
