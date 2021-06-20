package com.test.demo.dto;

import com.test.demo.model.User;

public class UserDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public UserDto(User user){
        this.name = user.getName();
        this.id = user.getId();
    }
}
