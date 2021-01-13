package com.example.filmland_subscription_microservice.controller;

import com.example.filmland_subscription_microservice.dto.SubscribeDTO;
import com.example.filmland_subscription_microservice.model.Subscription;
import com.example.filmland_subscription_microservice.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filmland")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/all-subscriptions")
    public ResponseEntity<List<Subscription>> getAllSubscriptions(){
        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/all-user-subscriptions")
    public ResponseEntity<List<Subscription>> getAllUserSubscriptions(@RequestParam String email){
        return new ResponseEntity<>(subscriptionService.getAllUserSubscriptions(email), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToCategory(@RequestBody SubscribeDTO subscribeDTO){
        subscriptionService.subscribeToCategory(subscribeDTO);
        return  new ResponseEntity<>("subscribed to category " + subscribeDTO.getAvailableCategory(), HttpStatus.OK);
    }

}
