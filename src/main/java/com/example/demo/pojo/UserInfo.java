package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo {
    private String username;
    private String password;
    private String email;
    private String blockchainAddress;
    private String blockchainPrivateKey;
    private String certificateNumber;
    private int volunteerHours;

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", blockchainAddress='" + blockchainAddress + '\'' +
                ", blockchainPrivateKey='" + blockchainPrivateKey + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", volunteerHours=" + volunteerHours +
                '}';
    }
}
