package com.example.demo;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void insertUser(){
            User user = new User();
            user.setUsername("haha");
            user.setPassword("dsaads");
            userMapper.insertUser(user);
    }

    @Test
    public void findUser(){
        User user = userMapper.findUserByUserName("gao");
        System.out.println(user);
    }

}
