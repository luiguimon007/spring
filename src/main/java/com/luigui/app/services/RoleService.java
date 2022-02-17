package com.luigui.app.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import com.luigui.app.entities.Role;
import com.luigui.app.entities.User;
import com.luigui.app.repositories.RoleRepository;
import com.luigui.app.repositories.UserInRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;
    
    @RolesAllowed("ROLE_ADMIN")
    public List<User> getUsersByRole(String roleName){
        return userInRoleRepository.findUsersByRoleName(roleName);
    }
    public List<Role> getRoles() {
        return (List<Role>) roleRepository.findAll();
    }
    public Role createRole(Role role){
        return roleRepository.save(role);
    }
    public Role updateRole(Role role,Integer roleId){
        Optional<Role> result =roleRepository.findById(roleId);
        if(result.isPresent()){
            return roleRepository.save(role);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role id %d doesnt exists",roleId));
        }
    }
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }
}
