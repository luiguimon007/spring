package com.luigui.app.repositories;

import com.luigui.app.entities.UserInRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInRoleRepository extends JpaRepository<UserInRole,Integer> {
    
}
