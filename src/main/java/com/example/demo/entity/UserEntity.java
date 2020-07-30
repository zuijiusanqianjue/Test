package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getId() {
        return id;
    }
}
