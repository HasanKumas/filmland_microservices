package com.example.filmland_user_micro_service.service;

import com.example.filmland_user_micro_service.dto.RequestUser;
import com.example.filmland_user_micro_service.model.UserAccount;
import com.example.filmland_user_micro_service.repository.UserAccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Component
public class UserAccountService implements UserDetailsService {
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
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

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
     * finds a user from database
     * @param userName userName
     */
    public Long getUserID(String userName) {

        UserAccount existingUser = userAccountRepository.findOneByUserNameIgnoreCase(userName);
        return existingUser.getId();
    }
    /**
     * finds a all users from database
     */
    public List getAllUsers() {

        return userAccountRepository.findAll();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * introduces user to spring security
     * @param userEmail email
     * @return UserDetails
     * @throws UsernameNotFoundException username not found
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findOneByUserNameIgnoreCase(userEmail);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(user.getUserName(), user.getPassword(), authorities);

    }

}
