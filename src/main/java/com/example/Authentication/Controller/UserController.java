package com.example.Authentication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.Entity.User;
import com.example.Authentication.Service.UserService;
import com.example.Authentication.Utils.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    private final ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> FindAll() {
        List<User> users = userService.findAll(); 
        return ResponseEntity.ok(apiResponse.sucessRes("users", users));
    }
}
