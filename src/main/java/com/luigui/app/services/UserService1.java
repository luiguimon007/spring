package com.luigui.app.services;

import java.util.List;
import java.util.Optional;

import com.luigui.app.entities.User;
import com.luigui.app.repositories.UserRepository;

import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService1 {
    private static final Logger log = LoggerFactory.getLogger(UserService1.class);
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(int page,int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
    public Page<String> getUsersnames(int page,int size) {
        return userRepository.findUsernames(PageRequest.of(page, size));
    }
    public User getUserById(Integer id) {
       return userRepository.findById(id).orElseThrow(
           () -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not found",id)));
    }
    @CacheEvict("users")
    public void deleteUserByUsername(String username) {
        User user = getUserByUsername(username);
        userRepository.delete(user);
    }
    @Cacheable("users")
    public User getUserByUsername(String username) {
        log.info("Getting user by username",username);
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();  
        }
        return userRepository.findByUsername(username).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username)));
    }
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username)));
    }
}
