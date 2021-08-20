package com.wy.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();
    public void initDataMethod(){
        System.out.println("initDataMethod");
        hashMap.put("10001","笑而过");
        hashMap.put("10002","库而过");
        hashMap.put("10003","阿毛过");
    }

    public void destroyDataMethod(){
        System.out.println("destroyDataMethod");
        hashMap.clear();
    }

    public String queryUserName(String uid){
        return hashMap.get(uid);
    }

}
