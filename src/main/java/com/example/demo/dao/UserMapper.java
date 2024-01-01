package com.example.demo.dao;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {

    /**
     * 注册
     */
    void insertUser(User user);

    User selectUserByUserName(String name);

}
