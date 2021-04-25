package com.example.demo.controller;

import com.example.demo.respository.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResource> create(@RequestBody() @Valid UserRequest user) {
        return ResponseEntity.ok(toResource(userService.create(toEntity(user))));
    }

    private static UserEntity toEntity(UserRequest user) {
        UserEntity entity = new UserEntity();
        entity.setAccountNumber(user.getAccountNumber());
        entity.setMobileNumber(user.getMobileNumber());
        entity.setBalance(user.getBalance());
        entity.setCurrency(user.getCurrency());
        return entity;
    }

    private static UserResource toResource(UserEntity entity) {
        UserResource resource = new UserResource();
        resource.setAccountNumber(entity.getAccountNumber());
        resource.setUuid(entity.getUuid());
        resource.setStatus(entity.getStatus());
        return resource;
    }
}
