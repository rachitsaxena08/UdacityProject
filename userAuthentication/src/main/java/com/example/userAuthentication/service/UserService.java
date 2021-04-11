package com.example.userAuthentication.service;


import com.example.userAuthentication.mapper.UserMapper;
import com.example.userAuthentication.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;


    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        final var encodedSalt = Base64.getEncoder().encodeToString(salt);
        final var hashedPassword = hashService.getHashedValue(user.getPassword(),encodedSalt);
        return userMapper.insert(new User(null,user.getUsername(),encodedSalt,hashedPassword,user.getFirstName(), user.getLastName()));
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }
}
