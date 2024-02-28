package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.User;
import com.example.yourgeekengineer.models.UserCredentialModal;
import com.example.yourgeekengineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncryptService passwordEncryptService;

    public boolean adminUserValidation(UserCredentialModal credentials) {
        Optional<User> user = userRepository.findUserByUserName(credentials.getUserName());
        return user.filter(value -> passwordEncryptService.matchPasswordWithEncrypt(credentials.getPassword(), value.getPassword())).isPresent();
    }
}
