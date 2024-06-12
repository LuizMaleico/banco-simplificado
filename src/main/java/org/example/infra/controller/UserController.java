package org.example.infra.controller;

import org.example.dtos.UserDTO;
import org.example.entity.UserEntity;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserEntity> userSave(@RequestBody UserDTO user){
        UserEntity newUser = service.createUser(user);
        service.UserSave(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
