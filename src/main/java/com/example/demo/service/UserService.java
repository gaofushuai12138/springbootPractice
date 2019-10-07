package com.example.demo.service;

import com.example.demo.bean.Message;
import com.example.demo.bean.User;

public interface UserService {

    public User Login(User user) throws Exception;

    public int addUser(User user) throws Exception;
}
