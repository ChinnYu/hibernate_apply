package com.cyl.model;

import java.util.Date;

/**
 * @author : Liu
 * @Date : 2019/10/25 下午 05:24
 * @Description :
 */

public class User {
    private int id;
    private String username;
    private String password;
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
