package com.example.Authentication.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.DataTransferObject.UserSecretDto;
import com.example.Authentication.Entity.User;
import com.example.Authentication.Service.AuthService;
import com.example.Authentication.Service.UserService;
import com.example.Authentication.Utils.ApiResponse;
import com.example.Authentication.Utils.Validator;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    
    private final Validator validator = new Validator();
    private final ApiResponse apiResponse = new ApiResponse();

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> Register(@RequestBody Map<String, Object> body) {
        String username = body.get("username").toString();
        String email = body.get("email").toString();
        String pwd = body.get("password").toString();

        if (validator.isValidEmail(email)) {
            UserSecretDto isExistedEmail = userService.findByEmail(email);
            if (isExistedEmail != null) {
                return ResponseEntity
                    .badRequest()
                    .body(apiResponse.errorRes("email is already used"));
            }
            
            UserSecretDto newUser = authService.register(username, email, pwd);
            return ResponseEntity.ok(apiResponse.sucessRes("user", newUser));
        }
        return ResponseEntity
            .badRequest()
            .body(apiResponse.errorRes("invalid email input"));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> Login(@RequestBody Map<String, Object> body) {
        String email = body.get("email").toString();
        String pwd = body.get("password").toString();

        User isMatched = authService.login(email, pwd);
        if (isMatched != null) {
            return ResponseEntity.ok().body(apiResponse.sucessRes("user", isMatched));
        }
        return ResponseEntity.badRequest()
            .body(apiResponse.errorRes("invalid email or password"));
    }

}
