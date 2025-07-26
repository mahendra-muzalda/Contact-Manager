package com.scm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm20.services.impl.SecurityCustomUserDetailService;

@Configuration
// This class is used to configure security settings for the application
public class SecurityConfig {

    // user create and login using java code with n memory service

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user = User
    // .withDefaultPasswordEncoder() // Use default password encoder for simplicity
    // .username("admin")
    // .password("1234")
    // .roles("ADMIN", "USER")
    // .build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    // configure of authentication provider spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // userDetailsService ka object ko set karte hain
        daoAuthenticationProvider.setUserDetailsService(userDetailService); // Set your UserDetailsService here
        // passwordEncoder ko set karte hain
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // Set your password encoder here

        return daoAuthenticationProvider;
    }

    @Bean
    // This method configures the security filter chain for the application
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls ko configure kiya hai ki konsi public hai aur konsi authenticated hai
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/singup").permitAll();
            authorize.requestMatchers("user/**").authenticated();
            authorize.anyRequest().permitAll(); // Allow all other requests
        });

        // form default login
        // agar hame kuch bhi customize karna ho to yha se hoga : login page, logout
        // page, etc.
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login") // Custom login page
                    .loginProcessingUrl("/authenticate") // URL to submit the login form
                    .successForwardUrl("/user/dashboard") // Redirect after successful login
                    .failureUrl("/login?error=true") // Redirect on login failure
                    .usernameParameter("email") // Custom username parameter
                    .passwordParameter("password") // Custom password parameter
                    .permitAll(); // Allow all users to access the login page
        });

        return httpSecurity.build();
    }

    // This method provides a PasswordEncoder bean that uses BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
