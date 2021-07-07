package com.snbc.testtemp.bean;

import java.io.Serializable;

/**
 * author: zhougaoxiong
 * date: 2020/9/4,16:11
 * projectName:IPC_Project
 * packageName:com.snbc.testtemp.bean
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4890858024154179452L;

    public int id;
    public int age;
    public String name;

    public User(int id, int age, String name) {
        this.id=id;
        this.age=age;
        this.name=name;
    }
}
