package com.xuecheng.entities;

public class UserIno {
    private String id;
    private String username;
    private String age;

    private UserAddress userAddress;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public UserAddress getAddress() {
        return userAddress;
    }

    public void setAddress(UserAddress address) {
        this.userAddress = address;
    }
}
