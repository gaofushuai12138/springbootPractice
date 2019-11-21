package com.example.demo.controller;

import com.example.demo.bean.Message;
import com.example.demo.bean.User;
import com.example.demo.config.sercurity.PermissionService;
import com.example.demo.service.UserService;

import com.example.demo.untils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RestController
@Api(value = "用户信息查询",tags = {"用户信息的controller"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PermissionService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }


    @RequestMapping(value = "/authentication/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录接口",notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户名",value = "username")
    })
    public Message LoginForUser(@RequestBody User user){
        try {
            String pwd = encodePassword(user.getPassword());
            System.out.println("================="+pwd);
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken( user.getUsername(),pwd);
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User userInfo = (User)userDetailsService.loadUserByUsername(user.getUsername());
            final String token = jwtTokenUtil.generateToken(userInfo);
            if(userInfo != null){
                return new Message(Message.SUCCESS,"登录成功!",token);
            }else {
                return new Message(Message.FAILURE,"没有此用户!",null);
            }
        }catch (Exception e){
//            System.out.println(e);
            e.printStackTrace();
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
