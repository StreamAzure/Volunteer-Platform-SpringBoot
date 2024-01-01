package com.example.demo.service.impl;

import com.example.demo.chainUtils.ChainAccountUtil;
import com.example.demo.chainUtils.ContractHelper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.RegistrationRequest;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserInfo;
import com.example.demo.service.UserService;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public CryptoKeyPair register(RegistrationRequest registrationRequest) {
        User user = new User();
        BeanUtils.copyProperties(registrationRequest, user);
        CryptoKeyPair account = ChainAccountUtil.createAccount();
        user.setBlockchainPrivateKey(account.getHexPrivateKey());
        user.setBlockchainAddress(account.getAddress());

        System.out.println("UserService:");
        System.out.println(user);

        ContractHelper.init(user.getBlockchainPrivateKey());
        ContractHelper.register(user.getUsername());

        userMapper.insertUser(user);
        return account;
    }

    @Override
    public UserInfo getUserByUsername(String username) {
        UserInfo userInfo = new UserInfo();
        User user = userMapper.selectUserByUserName(username);
        BeanUtils.copyProperties(user, userInfo);

        BigInteger volunteerHours = BigInteger.valueOf(-1);
        String certificateNumber;
        ContractHelper.init(user.getBlockchainPrivateKey());
        // 通过智能合约查询时长
        try {
            volunteerHours = ContractHelper.getVolunteerServiceHours();
            System.out.println("Hours："+ volunteerHours);
        } catch (ContractException e) {
            throw new RuntimeException(e);
        }

        // 通过智能合约查询证书编号
        try{
            certificateNumber = String.valueOf(ContractHelper.getCertificateId());
            System.out.println("certificateNumber: " + certificateNumber);
        } catch (ContractException e) {
            throw new RuntimeException(e);
        }
        userInfo.setVolunteerHours(volunteerHours.intValue());
        userInfo.setCertificateNumber(certificateNumber);

        System.out.println("UserService:");
        System.out.println(userInfo.toString());
        return userInfo;
    }
}
