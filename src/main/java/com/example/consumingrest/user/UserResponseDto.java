package com.example.consumingrest.user;

public record UserResponseDto(long id, String name, String email) {
    static public UserResponseDto fromEntity(UserEntity entity) {
        return new UserResponseDto(entity.id, entity.name, entity.email);
    }
}