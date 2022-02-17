package com.luigui.app.repositories;

import java.util.List;

import com.luigui.app.entities.User;
import com.luigui.app.entities.UserInRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInRoleRepository extends JpaRepository<UserInRole,Integer> {
    
    @Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
    public List<User> findUsersByRoleName(String roleName);

    public List<UserInRole> findByUser(User user);
}
