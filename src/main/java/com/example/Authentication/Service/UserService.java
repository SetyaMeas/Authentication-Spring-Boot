package com.example.Authentication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.DataTransferObject.UserSecretDto;
import com.example.Authentication.Entity.User;
import com.example.Authentication.Repository.SecretRepo;
import com.example.Authentication.Repository.UserRepo;

import lombok.NonNull;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final SecretRepo secretRepo;

    @Autowired
    public UserService(UserRepo userRepo, SecretRepo secretRepo) {
        this.userRepo = userRepo;
        this.secretRepo = secretRepo;
    }

    public User create(String username) {
        User newUser = new User();
        newUser.setUsername(username);
        return userRepo.save(newUser);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(@NonNull Long userId) {
        return userRepo.findById(userId);
    }

    public void deleteById(@NonNull Long userId) {
        userRepo.deleteById(userId);
    }

    public User updateUsername(@NonNull Long userId, @NonNull String username) {
        userRepo.updateUsername(userId, username);
        return (User) findById(userId).orElse(new User());
    }

    public UserSecretDto findByEmail(@NonNull String email) {
        return secretRepo.findByEmail(email);
    }
}
