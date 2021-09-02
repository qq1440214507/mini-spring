package com.wy.springframework.test.bean;

public class AopUserService implements IUserService{
    private String token;
    @Override
    public String queryUserInfo() {
        return "小福哥"+token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
