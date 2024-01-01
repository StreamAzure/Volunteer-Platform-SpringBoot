package com.example.demo.chainUtils;

import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;

import java.math.BigInteger;

public class ChainAccountUtil {
    public static CryptoKeyPair createAccount(){
        // 创建非国密类型的CryptoSuite
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        // 随机生成非国密公私钥对
        return cryptoSuite.generateRandomKeyPair();
    }

    public static CryptoKeyPair loadAccountFromHexPrivateKey(String privateKey)
    {
        // 根据cryptoType创建cryptoSuite，cryptoType目前支持：
        // 1. CryptoType.ECDSA_TYPE: 用于创建非国密类型的CryptoSuite
        // 2. CryptoType.SM_TYPE:    用于创建国密类型的CryptoSuite
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        // 从十六进制私钥字符串hexPrivateKey加载私钥对象
        return cryptoSuite.getKeyPairFactory().createKeyPair(privateKey);
    }
}
