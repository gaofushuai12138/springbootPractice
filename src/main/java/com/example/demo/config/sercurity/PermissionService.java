package com.example.demo.config.sercurity;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PermissionService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = (User)userService.findUserByUserName(s);
            if(user == null){
                throw new UsernameNotFoundException("用户不存在");
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
