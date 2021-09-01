package com.wy.springframework.test.bean;

import com.wy.springframework.steretype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();
    static {
        hashMap.put("10001","xxx");
        hashMap.put("10002","xxqwe11x");
        hashMap.put("10003","asdad");
    }
    public String queryUserName(String uid){
        return hashMap.get(uid);
    }
}
