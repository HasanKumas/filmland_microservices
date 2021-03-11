package com.example.filmland_subscription_microservice.service;

import com.example.filmland_subscription_microservice.FilmlandSubscriptionMicroserviceApplication;
import com.example.filmland_subscription_microservice.dto.CategoryDTO;
import com.example.filmland_subscription_microservice.dto.SubscribeDTO;
import com.example.filmland_subscription_microservice.model.Subscription;
import com.example.filmland_subscription_microservice.repository.SubscriptionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class SubscriptionService {
    private  final SubscriptionRepository subscriptionRepository;
    private final FilmlandSubscriptionMicroserviceApplication.CategoryClient categoryClient;
    private final FilmlandSubscriptionMicroserviceApplication.UserAccountClient userAccountClient;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, FilmlandSubscriptionMicroserviceApplication.CategoryClient categoryClient, FilmlandSubscriptionMicroserviceApplication.UserAccountClient userAccountClient) {
        this.subscriptionRepository = subscriptionRepository;
        this.categoryClient = categoryClient;
        this.userAccountClient = userAccountClient;
    }

    /**
     * subcribe a user to a category
     * @param subscribeDTO subscribe request
     */
    public void subscribeToCategory(SubscribeDTO subscribeDTO) {

        Subscription newSubscription = new Subscription();
        CategoryDTO availableCategory = categoryClient.getCategory(subscribeDTO.getAvailableCategory());

        newSubscription.setCategoryID(availableCategory.getId());
        newSubscription.setRemainingContent(availableCategory.getAvailableContent());
        newSubscription.setStartDate(LocalDate.now());
        newSubscription.setPaymentDate(LocalDate.now().plusMonths(1));
        newSubscription.getUserIDs().add(userAccountClient.getUserID(subscribeDTO.getEmail()));
        subscriptionRepository.save(newSubscription);
    }
    /**
     * cancel a subscription from database
     * @param subscriptionName subscription categoryName
     */
    public void cancelSubscription(String subscriptionName, String email) {

        Optional<Subscription> existingSubscription = getSubscription(subscriptionName, email);
        existingSubscription.ifPresent(subscription -> subscriptionRepository.deleteById(subscription.getId()));
    }
    /**
     * finds a subscription of a user from database
     * @param subscriptionName subscription categoryName
     */
    public Optional<Subscription> getSubscription(String subscriptionName, String email) {

        Long userID = userAccountClient.getUserID(email);
        Long categoryID = categoryClient.getCategoryID(subscriptionName);
        List<Subscription> existingSubscriptions = subscriptionRepository.findAllByCategoryID(categoryID);
        return existingSubscriptions.stream()
                .filter(subscription -> subscription.getUserIDs().stream()
                                                .anyMatch(userId -> userId.equals(userID)))
                                        .findFirst();

    }
    /**
     * finds all subscriptions from database
     */
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> existingSubscriptions = new ArrayList<>();

        existingSubscriptions = subscriptionRepository.findAll();
        return existingSubscriptions;
    }
    /**
     * finds all subscriptions of a user from database
     */
    public List<Subscription> getAllUserSubscriptions(String email) {
        Long userID = userAccountClient.getUserID(email);
        List<Subscription> existingSubscriptions;
        existingSubscriptions = subscriptionRepository.findAll();

        return existingSubscriptions.stream()
                .filter(subscription -> subscription.getUserIDs().stream()
                        .allMatch(userId -> userId.equals(userID))).collect(Collectors.toList());
    }
}
