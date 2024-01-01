package com.example.demo.chainUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.contract.Volunteer;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

import java.math.BigInteger;

public class ContractHelper {
    static final String contractAddr = "0xd24180cc0fef2f3e545de4f9aafc09345cd08903";
    static Volunteer volunteerContract;
    public static final String configFile = ContractHelper.class.getClassLoader().getResource("config.toml").getPath();
    public static void init(String privateKey){
        // 初始化BcosSDK
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");

        CryptoKeyPair cryptoKeyPair =  ChainAccountUtil.loadAccountFromHexPrivateKey(privateKey);
        // 加载智能合约
        volunteerContract = Volunteer.load(contractAddr, client, cryptoKeyPair);
    }

    // 用户信息上链
    public static void register(String _username){
        volunteerContract.register(_username);
    }

    // 登记志愿时长
    public static void logServiceHours(BigInteger _hours) {
        volunteerContract.logServiceHours(_hours);
    }

    // 获取用户志愿时长
    public static BigInteger getVolunteerServiceHours() throws ContractException {
        return volunteerContract.getVolunteerServiceHours();
    }

    // 申领志愿证书
    public static void applyCertificate(){
        volunteerContract.applyCertificate();
    }

    // 查询当前用户志愿证书编号
    public static BigInteger getCertificateId() throws ContractException {
        return volunteerContract.getCertificateIdByAddress();
    }

    // 根据证书编号查询证书信息
    public static JSONObject getCertificateInfo(BigInteger _certificatedId) throws ContractException {
        Tuple3<BigInteger, Boolean, String> res = volunteerContract.getCertificateInfo(_certificatedId);
        JSONObject info = new JSONObject();
        info.put("certificateId", res.getValue1());
        info.put("isValid", res.getValue2());
        info.put("owner", res.getValue3());
        return info;
    }
}
