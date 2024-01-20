package com.example.Authentication.DataTransferObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private Long userId;
    private String email;
    private String pwd;

    public LoginDto(Long userId, String email, String pwd) {
        this.userId = userId;
        this.email = email;
        this.pwd = pwd;
    }
}
