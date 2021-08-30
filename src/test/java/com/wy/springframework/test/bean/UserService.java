package com.wy.springframework.test.bean;

import com.wy.springframework.steretype.Component;

@Component("userService")
public class UserService implements IUserService{
    private String token;

    @Override
    public String queryUserInfo() {
        return "hello world";
    }
    public String register(String userName){
        return "注册用户:"+userName+"success!";
    }

    @Override
    public String toString() {
        return "UserService#token={ "+token+"}";
    }

    public void setToken(String token) {
        this.token = token;
    }
}
