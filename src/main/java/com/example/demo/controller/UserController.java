package com.example.demo.controller;

import com.example.demo.bean.Message;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Message LoginForUser(@RequestBody User user){
        try {
            User userInfo = userService.Login(user);
            if(userInfo != null){
                return new Message(Message.SUCCESS,"登录成功!",userInfo);
            }else {
                return new Message(Message.FAILURE,"没有此用户!",null);
            }
        }catch (Exception e){
            return new Message(Message.FAILURE,"登录失败!",null);
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Message addUser(@RequestBody User user){
        try {
            int res = userService.addUser(user);
            if(res != -1){
                return new Message(Message.SUCCESS,"添加用户成功!",null);
            }else {
                return new Message(Message.FAILURE,"添加用户失败!",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Message(Message.FAILURE,"添加用户失败!",null);
        }
    }
}
