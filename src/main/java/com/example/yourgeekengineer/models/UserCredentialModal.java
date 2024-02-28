package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialModal {

    private String userName;
    private String password;


    public UserCredentialModal(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
