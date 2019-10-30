package com.cyl.model;

import java.util.Set;

/**
 * @author : Liu
 * @Date : 2019/10/28 下午 11:36
 * @Description :
 */

public class Classes {
    private int id;
    private String name;
    private Set<Teacher> teachers;

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
