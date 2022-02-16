package com.luigui.app.repositories;

import java.util.List;

import com.luigui.app.entities.Address;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer>{
    
    @Query("SELECT a FROM Address a  WHERE a.profile.user.id=?1 AND a.profile.id=?2")
    List<Address> findByProfileId(Integer userId,Integer profileId);
}
