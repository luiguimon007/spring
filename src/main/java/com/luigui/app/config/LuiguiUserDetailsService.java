package com.luigui.app.config;

import java.util.List;
import java.util.Optional;

import com.luigui.app.entities.User;
import com.luigui.app.entities.UserInRole;
import com.luigui.app.repositories.UserInRoleRepository;
import com.luigui.app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LuiguiUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isPresent()) {
            User user = optional.get();
            List<UserInRole> users = userInRoleRepository.findByUser(user);
            String []roles = users.stream().map(r -> r.getRole().getName()).toArray(String[]::new);//transformar roles a un String[] 
            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
        }else{
            throw new UsernameNotFoundException("Username "+username+" not found");
        }
        
    }
    
}
