package com.wy.springframework.test.bean;

import com.wy.springframework.beans.factory.annotation.Autowired;
import com.wy.springframework.beans.factory.annotation.Value;
import com.wy.springframework.steretype.Component;

@Component("userService")
public class UserService implements IUserService{
    @Value("${token}")
    private String token;
    @Autowired
    private UserDao userDao;
    @Override
    public String queryUserInfo() {
        return "注册用户:"+userDao.queryUserName("10001")+"success!";
    }
    public String register(String userName){
        return "注册用户:"+userDao.queryUserName("10001")+"success!";
    }

    @Override
    public String toString() {
        return "UserService#token={ "+token+"}";
    }

    public void setToken(String token) {
        this.token = token;
    }
}
