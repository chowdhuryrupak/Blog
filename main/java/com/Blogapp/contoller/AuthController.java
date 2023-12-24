package com.Blogapp.contoller;

import com.Blogapp.Dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/auth")
public class AuthController {

    @PostMapping("singin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        return null;
    }

}
