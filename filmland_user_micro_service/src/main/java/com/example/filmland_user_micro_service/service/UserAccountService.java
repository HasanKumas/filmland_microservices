package com.example.filmland_user_micro_service.service;

import com.example.filmland_user_micro_service.dto.RequestUser;
import com.example.filmland_user_micro_service.model.UserAccount;
import com.example.filmland_user_micro_service.repository.UserAccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * adds a new user to database
     * @param user user
     */
    public void addUser(RequestUser user) {

        UserAccount newUser = new UserAccount();
        newUser.setUserName(user.getEmail());
        newUser.setPassword(user.getPassword());

        userAccountRepository.save(newUser);
    }
    /**
     * deletes a user account from database
     * @param userName userName
     */
    public void deleteUserAccount(String userName) {

        UserAccount existingUser = userAccountRepository.findOneByUserNameIgnoreCase(userName);
        userAccountRepository.delete(existingUser);
    }
    /**
     * finds a category from database
     * @param userName userName
     */
    public Long getUserID(String userName) {

        UserAccount existingUser = userAccountRepository.findOneByUserNameIgnoreCase(userName);
        return existingUser.getId();
    }

}
