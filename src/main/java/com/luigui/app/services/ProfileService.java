package com.luigui.app.services;

import java.util.Optional;

import com.luigui.app.entities.Profile;
import com.luigui.app.entities.User;
import com.luigui.app.repositories.ProfileRepository;
import com.luigui.app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;
    
    public Profile create(Profile profile,Integer userId){
        Optional<User> user =userRepository.findById(userId);
        if(user.isPresent()) {
            profile.setUser(user.get());
            return profileRepository.save(profile);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not found",userId));
        }
    }

    public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
        return profileRepository.findByUserIdAndProfileId(userId,profileId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not found and profile %d not found",userId,profileId)));
    }
}
