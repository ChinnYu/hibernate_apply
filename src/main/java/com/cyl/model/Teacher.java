package com.cyl.model;

import java.util.Set;

/**
 * @author : Liu
 * @Date : 2019/10/28 下午 11:34
 * @Description :
 */

public class Teacher {
    private int id;
    private String name;
    private Set<Classes> classesSet;

    public Set<Classes> getClassesSet() {
        return classesSet;
    }

    public void setClassesSet(Set<Classes> classesSet) {
        this.classesSet = classesSet;
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

    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Teacher() {
    }
}
