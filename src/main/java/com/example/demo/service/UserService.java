package com.example.demo.service;

import com.example.demo.pojo.RegistrationRequest;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserInfo;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;

public interface UserService {

    CryptoKeyPair register(RegistrationRequest registrationRequest);

    UserInfo getUserByUsername(String username);

}
