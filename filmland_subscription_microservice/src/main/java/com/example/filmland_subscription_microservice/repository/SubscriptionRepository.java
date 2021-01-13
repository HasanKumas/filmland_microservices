package com.example.filmland_subscription_microservice.repository;

import com.example.filmland_subscription_microservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByCategoryID(Long categoryID);
}
