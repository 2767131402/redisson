package com.zhenglei.redisson.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class A {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("id","");

        Gson gson = new GsonBuilder().registerTypeAdapter(Long.class, new StringTypeAdapter()).create();
        User user = gson.fromJson(gson.toJson(map),new TypeToken<User>() {}.getType());
        System.err.println(user);
    }
}
