package com.example.consumingrest.user;

import java.util.List;

public interface UserServiceInterface {
    public void create(UserDto user);
    public List<UserEntity> getAll();

    public UserEntity getByName(String name);
}
