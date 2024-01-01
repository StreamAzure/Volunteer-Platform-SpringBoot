package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String username;
    private String password;
    private String email;
    private String blockchainAddress;
    private String blockchainPrivateKey;
    private String certificateNumber;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", blockchainAddress='" + blockchainAddress + '\'' +
                ", blockchainPrivateKey='" + blockchainPrivateKey + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                '}';
    }
}
