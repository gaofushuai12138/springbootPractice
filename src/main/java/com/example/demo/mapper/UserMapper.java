package com.example.demo.mapper;


import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User findSingleUser(User user);

    User findUserByUserName(String username);


}
