package com.luigui.app.models;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

//@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
@PostAuthorize("hasRole('ROLE_ADMIN')")
public interface LuiguiSecurityRule {
    
}
