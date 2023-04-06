package com.example.consumingrest.user;

import com.example.consumingrest.EntityConflictException;
import com.example.consumingrest.apiError.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws EntityConflictException {
        this.userService.create(userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserEntity> users = this.userService.getAll();
        List<UserResponseDto> mappedUsers = users.stream().map(user -> UserResponseDto.fromEntity(user)).collect(Collectors.toList());

        return new ResponseEntity<List<UserResponseDto>>(mappedUsers, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponseDto> getUserByName(@PathVariable String name) {
        UserEntity entity = this.userService.getByName(name);

        return new ResponseEntity<UserResponseDto>(UserResponseDto.fromEntity(entity), HttpStatus.OK);
    }
}
