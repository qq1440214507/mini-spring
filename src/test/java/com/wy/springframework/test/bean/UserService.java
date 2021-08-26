package com.wy.springframework.test.bean;


public class UserService implements IUserService{

    @Override
    public String queryUserInfo() {
        return "hello world";
    }
    public String register(String userName){
        return "注册用户:"+userName+"success!";
    }
}
