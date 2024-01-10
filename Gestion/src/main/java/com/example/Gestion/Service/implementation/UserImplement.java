package com.example.Gestion.Service.implementation;


import com.example.Gestion.Models.User;
import com.example.Gestion.Repository.UserRepository;
import com.example.Gestion.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user) ;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }


}
