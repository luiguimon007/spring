package com.luigui.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //super.configure(auth);
        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN")
        .and().withUser("user").password(encoder().encode("password")).roles("USER");
    }
    @Bean
    public PasswordEncoder encoder() throws Exception{
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable().authorizeRequests().antMatchers("/user/**").hasRole("ADMIN")
        .antMatchers("/roles/**").permitAll().anyRequest().authenticated()
        .and().httpBasic();
    }
    
}
