package com.luigui.app.controllers;

import java.util.List;

import com.luigui.app.entities.Role;
import com.luigui.app.entities.User;
import com.luigui.app.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//quien se autentifico
        log.info("Name {} ",authentication.getName());
        log.info("Principal {}",authentication.getPrincipal());
        log.info("Credentials {}",authentication.getPrincipal());
        log.info("Roles {}",authentication.getAuthorities().toString());
        return new ResponseEntity<List<Role>>(roleService.getRoles(),HttpStatus.OK);
    }
   
    @GetMapping("/{roleName}/users")
    public ResponseEntity<List<User>> getUserByRole(@PathVariable("roleName")String roleName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Name {} ",authentication.getName());
        log.info("Principal {}",authentication.getPrincipal());
        log.info("Credentials {}",authentication.getPrincipal());
        log.info("Roles {}",authentication.getAuthorities().toString());
        return new ResponseEntity<List<User>>(roleService.getUsersByRole(roleName),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Role> createRoles(@RequestBody Role role){
        return new ResponseEntity<Role>(roleService.createRole(role),HttpStatus.CREATED);
    }
    @PutMapping("/{roleId}")  
    public ResponseEntity<Role> updateRoles(@RequestBody Role role,@PathVariable("roleId") Integer roleId){
        return new ResponseEntity<Role>(roleService.updateRole(role, roleId),HttpStatus.OK);
    } 
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRoles(@PathVariable("roleId") Integer roleId){
        roleService.deleteRole(roleId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
