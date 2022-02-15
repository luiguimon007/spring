package com.luigui.app.Repositories;

import com.luigui.app.entities.Profile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,Integer> {
    
}
