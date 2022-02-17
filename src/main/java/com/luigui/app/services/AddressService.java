package com.luigui.app.services;

import java.util.List;
import java.util.Optional;

import com.luigui.app.entities.Address;
import com.luigui.app.entities.Profile;
import com.luigui.app.repositories.AddressRepository;
import com.luigui.app.repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Address> findAddressByProfileAndUserId(Integer userId, Integer profileId) {
        return addressRepository.findByProfileId(userId, profileId);
    }

    public Address createAddress(Integer userId, Integer profileId, Address address) {
        Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
        if(result.isPresent()) {
            address.setProfile(result.get());
            return addressRepository.save(address);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profile %d and user not found", userId, profileId));
        }
        
    }
}
