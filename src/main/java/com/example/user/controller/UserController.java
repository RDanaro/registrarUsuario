package com.example.user.controller;

import com.example.user.dto.UserRequest;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/users", produces = {"application/json"})
    public ResponseEntity createUser (@Valid @RequestBody UserRequest user) throws Exception {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @GetMapping(value="/findUser" , produces = {"application/json"})
    public ResponseEntity findUserByEmail (@Valid @RequestParam("email") String email) throws Exception  {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }
}