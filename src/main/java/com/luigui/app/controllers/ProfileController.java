package com.luigui.app.controllers;

import com.luigui.app.entities.Profile;
import com.luigui.app.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    
    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("userId") Integer userId,
        @PathVariable("profileId") Integer profileId){
        
            return new ResponseEntity<>(profileService.getByUserIdAndProfileId(userId, profileId),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile,@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(profileService.create(profile, userId),HttpStatus.CREATED);
    }
    
}
