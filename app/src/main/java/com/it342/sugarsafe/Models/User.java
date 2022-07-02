package com.it342.sugarsafe.Models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String userName;
    private String email;
    private String age;
    private String passwod;

    public User(String id, String userName, String email, String age, String passwod) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
        this.passwod = passwod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getUserAfterMapping(){
        Map<String, Object> map=new HashMap<>();
        map.put("USERID",getId());
        map.put("USERNAME",getUserName());
        map.put("EMAIL",getEmail());
        map.put("AGE",getAge());
        map.put("PASSWORD",getPasswod());

        return  map;
    }
}
