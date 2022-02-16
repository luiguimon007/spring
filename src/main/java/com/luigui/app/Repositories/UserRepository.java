package com.luigui.app.Repositories;

import java.util.List;
import java.util.Optional;

import com.luigui.app.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    //query methods
    public Optional<User> findByUsername(String username);  
    public Optional<User> findByUsernameAndPassword(String username, String password);
    /**
     * Esto no es SQL se llama JPQL customs querys
     * @return
     */
    @Query("SELECT u.username FROM User u WHERE u.username LIKE '%collins'")
    public Page<String> findUsernames(Pageable pageable);
}
  