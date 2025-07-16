package com.scm.scm20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm20.entities.User;
import com.scm.scm20.repositories.UserRepository;
import com.scm.scm20.services.UserService;

import helper.ResourceNotFoundException;

@Service
public class UserServicesImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public User saveUser(User user) {

        //userId have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encode

       
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAbout(user.getAbout());
        existingUser.setPassword(user.getPassword());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProvider(user.getProvider());
        existingUser.setProviderUserId(user.getProviderUserId());

        //save the user to database
        User updatedUser = userRepository.save(existingUser);

        return Optional.ofNullable(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        User existingUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));

        userRepository.delete(existingUser);
    }

    @Override
    public boolean isUserExist(String id) {
        User existingUser = userRepository.findById(id).orElse(null);

        return existingUser != null ? true : false ;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User existingUser = userRepository.findByEmail(email).orElse(null);

        return existingUser != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        
        return userRepository.findAll();
    }

}
