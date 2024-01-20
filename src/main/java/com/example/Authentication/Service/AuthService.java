package com.example.Authentication.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.Authentication.DataTransferObject.LoginDto;
import com.example.Authentication.DataTransferObject.UserSecretDto;
import com.example.Authentication.Entity.User;

@Service
public class AuthService {
    private final UserService userService;
    private final SecretService secretService;

    public AuthService(UserService userService, SecretService secretService) {
        this.userService = userService;
        this.secretService = secretService;
    }

    public UserSecretDto register(String username, String email, String pwd) {
        String hashedPWd = BCrypt.hashpw(pwd, BCrypt.gensalt(12));
        User newUser = userService.create(username);
        secretService.create(newUser, email, hashedPWd);

        UserSecretDto userSecretDto = new UserSecretDto(newUser.getUserId(), username, email);
        return userSecretDto;
    }

    public User login(String email, String pwd) {
        LoginDto userTarget = secretService.findByEmailForLogin(email);
        if (userTarget == null) {
            return null;
        }

        boolean isMatched = BCrypt.checkpw(pwd, userTarget.getPwd());

        if (isMatched) {
            return (User) userService.findById(userTarget.getUserId()).orElse(new User());
        }
        return null;
    }
}
