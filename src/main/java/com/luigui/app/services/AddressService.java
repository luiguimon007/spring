package com.luigui.app.services;

import java.util.List;

import com.luigui.app.entities.Address;
import com.luigui.app.repositories.AddressRepository;
import com.luigui.app.repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Address> findAddressByProfileAndUserId(Integer userId, Integer profileId) {
        return addressRepository.findByProfileId(userId, profileId);
    }
}
