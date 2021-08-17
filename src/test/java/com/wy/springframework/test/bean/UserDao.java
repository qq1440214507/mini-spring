package com.wy.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();
    static {
        hashMap.put("10001","小哥");
        hashMap.put("10002","八杯水");
        hashMap.put("10003","阿毛");
    }
    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}