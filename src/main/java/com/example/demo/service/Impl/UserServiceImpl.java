package com.example.demo.service.Impl;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User Login(User user) throws Exception {
        User userInfo = userMapper.findSingleUser(user);
        if(userInfo != null){
            return userInfo;
        }
        return null;
    }

    @Override
    public int addUser(User user) throws Exception {
        int resOfAdd = -1;
        resOfAdd = userMapper.insertUser(user);
        return resOfAdd;
    }

    @Override
    public User findUserByUserName(String username) throws Exception {
        return userMapper.findUserByUserName(username);
    }
}
