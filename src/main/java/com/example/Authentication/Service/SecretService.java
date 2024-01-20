package com.example.Authentication.Service;

import org.springframework.stereotype.Service;

import com.example.Authentication.DataTransferObject.LoginDto;
import com.example.Authentication.Entity.Secret;
import com.example.Authentication.Entity.User;
import com.example.Authentication.Repository.SecretRepo;

@Service
public class SecretService {
    private final SecretRepo secretRepo;

    public SecretService (SecretRepo secretRepo) {
        this.secretRepo = secretRepo;
    }

    public void create(User user, String email, String pwd) {
        Secret newSecret = new Secret();
        newSecret.setEmail(email);
        newSecret.setPwd(pwd);
        newSecret.setUser(user);
        secretRepo.save(newSecret);
    }

    public LoginDto findByEmailForLogin(String email) {
        return secretRepo.findByEmailForLogin(email);
    }
}
