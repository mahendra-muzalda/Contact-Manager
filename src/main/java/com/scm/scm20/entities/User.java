package com.scm.scm20.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(unique = true)
    private String phoneNumber;
    @Column(length = 1000)
    private String profilePic;
    @Getter(value = AccessLevel.NONE)
    private boolean enabled = true; // User is enabled by default
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    //SELF , GOOGLE , FACEBOOK , TWITTER , LINKEDIN, 
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // map contacts with users(one user can have many contacts)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    // roles of user
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //list of roles
        //collection of SimpleGrantedAuthority[roles{admin, user}]
         Collection<SimpleGrantedAuthority> roles  = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return roles; // Return the roles of the user as GrantedAuthority
    }

    //for this project
    //email is used as username
    @Override
    public String getUsername() {
       
        return this.email; // Return the email as the username
    }

    public boolean isAccountNonExpired() {
        return true; // Account is not expired
    }   

    public boolean isAccountNonLocked() {
        return true; // Account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return this.enabled; // Return the enabled status of the user
    }

    @Override
    public String getPassword() {
        return this.password; // Return the password of the user
    }


}
