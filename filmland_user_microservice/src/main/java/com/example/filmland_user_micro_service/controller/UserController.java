package com.example.filmland_user_micro_service.controller;

import com.example.filmland_user_micro_service.FilmlandUserMicroServiceApplication;
import com.example.filmland_user_micro_service.dto.RequestUser;
import com.example.filmland_user_micro_service.service.UserAccountService;
import org.apache.http.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("filmland")
public class UserController {
    private final UserAccountService userAccountService;

    private final FilmlandUserMicroServiceApplication.SubscriptionClient subscriptionClient;

    public UserController(UserAccountService userAccountService, FilmlandUserMicroServiceApplication.SubscriptionClient subscriptionClient) {
        this.userAccountService = userAccountService;
        this.subscriptionClient = subscriptionClient;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){

        return new ResponseEntity<>(subscriptionClient.getAllSubscriptions(), HttpStatus.OK);
    }

    /**
     * adds a new user
     * @param user user
     * @return confirmation text
     */
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody RequestUser user){

         userAccountService.addUser(user);
         return new ResponseEntity<>("user added", HttpStatus.OK);
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam String userName){

        userAccountService.deleteUserAccount(userName);
        return new ResponseEntity<>("user removed", HttpStatus.OK);
    }
    @GetMapping("/get-userID")
    public ResponseEntity<Long> getUserID(@RequestParam String email){

       return new ResponseEntity<>(userAccountService.getUserID(email), HttpStatus.OK);
    }
    @GetMapping("/get-all-users")
    public ResponseEntity<List> getAllUsers(){

       return new ResponseEntity<>(userAccountService.getAllUsers(), HttpStatus.OK);
    }
}
