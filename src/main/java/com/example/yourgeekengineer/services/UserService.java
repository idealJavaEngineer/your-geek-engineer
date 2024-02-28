package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.User;
import com.example.yourgeekengineer.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public boolean registerUser(User user) throws Exception {
        if (validateUserCredential(user)) {
            userRepository.save(user);
        }
        return true;
    }

    public User validateSignInUser(User signInUser) throws Exception {
        Optional<User> user = userRepository.findUserByEmail(signInUser.getEmail());
        if (user.isEmpty()) {
            user = userRepository.findUserByUserName(signInUser.getUserName());
            if (user.isEmpty()) {
                throw new Exception("username or email doesnt match");
            } else {
                User currUser = user.get();
                if (currUser.getPassword().equals(signInUser.getPassword())) {
                    return currUser;
                } else {
                    throw new Exception("Wrong Credentials");
                }
            }
        }
        return null;
    }

    /**
     * validate user credential that password should not match email and if username is empty
     * then set username same as email
     *
     * @param user contains user Credentials
     * @throws Exception
     */
    private boolean validateUserCredential(User user) throws Exception {
        if (user.getEmail().equalsIgnoreCase(user.getPassword())) {
            throw new Exception("password should not match with email");
        } else {
            if (user.getUserName() == null || user.getUserName().isBlank() || user.getUserName().isEmpty()) {
                user.setUserName(user.getEmail());
            }
        }
        return true;
    }
}
