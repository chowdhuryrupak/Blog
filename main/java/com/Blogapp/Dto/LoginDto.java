package com.Blogapp.Dto;

import lombok.Data;

@Data
public class LoginDto {
    private String  usernameOrEmail;
    private String password;
}
