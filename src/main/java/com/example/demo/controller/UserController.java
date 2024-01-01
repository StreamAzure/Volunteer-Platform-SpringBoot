package com.example.demo.controller;

import com.example.demo.pojo.LogRequest;
import com.example.demo.pojo.RegistrationRequest;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserInfo;
import com.example.demo.service.UserService;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody RegistrationRequest request) {
        // 处理注册逻辑，request 包含前端发送的数据
        System.out.println("收到注册请求：" + request.toString());
        // 业务处理注册信息，并返回区块链账户
        CryptoKeyPair cryptoKeyPair = userService.register(request);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("address", cryptoKeyPair.getAddress());
        responseMap.put("private_key", cryptoKeyPair.getHexPrivateKey());

        System.out.println("Controller: ");
        System.out.println(cryptoKeyPair.getAddress());
        System.out.println(cryptoKeyPair.getHexPrivateKey());
        return responseMap;
    }

    @GetMapping("/person")
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam String username) {
        // 在这里根据用户名查询用户信息，然后返回
        UserInfo userInfo = userService.getUserByUsername(username);
        return userInfo;
    }

}
