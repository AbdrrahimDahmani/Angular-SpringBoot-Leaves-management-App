package com.example.Gestion.Service;

import com.example.Gestion.Models.User;

import java.util.List;

public interface UserService {

        User createUser(User user);

        User getUserById(Long id);

        List<User> getAllUsers();

        User updateUser(Long id, User updatedUser);

        void deleteUser(Long id);


}


