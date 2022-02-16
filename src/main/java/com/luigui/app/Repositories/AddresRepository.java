package com.luigui.app.repositories;

import com.luigui.app.entities.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresRepository extends CrudRepository<Address,Integer>{
    
}
