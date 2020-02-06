package com.example.user.service;

import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;
import java.util.Date;


public interface UserService {

    public UserResponse createUser (UserRequest user) throws Exception;

    public UserResponse findUserByEmail (String email) throws Exception;
}
