package com.example.consumingrest.user;

import com.example.consumingrest.EntityConflictException;
import com.example.consumingrest.apiError.ApiError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class UserService implements UserServiceInterface {
    private final List<UserEntity> users = new ArrayList<UserEntity>();

    @Override
    public void create(UserDto user) {
        Random random = new Random();

        if(getByName(user.name)!=null){
            throw new EntityConflictException("Name", user.name);
        }
        if(getByEmail(user.email)!=null){
            throw new EntityConflictException("Email", user.email);
        }
        UserEntity entity = new UserEntity();
        entity.email = user.email;
        entity.name = user.name;
        entity.id = random.nextLong();

        users.add(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return this.users;
    }

    @Override
    public UserEntity getByName(String name) {
        UserEntity entity = null;

        for (int i = 0, length = users.size(); i < length; i++) {
            if (users.get(i).name.equals(name)) {
                entity = users.get(i);
            }
        }

        return entity;
    }
    @Override
    public UserEntity getByEmail(String email) {
        UserEntity entity = null;

        for (int i = 0, length = users.size(); i < length; i++) {
            if (users.get(i).email.equals(email)) {
                entity = users.get(i);
            }
        }

        return entity;
    }
}
