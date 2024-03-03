package com.example.yourgeekengineer.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptService {

    public String bCryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean matchPasswordWithEncrypt(String password, String hashPw) {
        return BCrypt.checkpw(password, hashPw);
    }
}
