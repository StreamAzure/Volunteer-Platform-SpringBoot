package com.example.demo.service.impl;

import com.example.demo.chainUtils.ContractHelper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.VolunteerService;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void logVolunteerHours(String username, int hours) {
        User user = userMapper.selectUserByUserName(username);
        ContractHelper.init(user.getBlockchainPrivateKey());
        ContractHelper.logServiceHours(BigInteger.valueOf(hours));
    }
    @Override
    public void applyCertificate(String username) {
        User user = userMapper.selectUserByUserName(username);
        ContractHelper.init(user.getBlockchainPrivateKey());
        ContractHelper.applyCertificate();
    }
}
