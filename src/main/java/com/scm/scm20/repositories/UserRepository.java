package com.scm.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm20.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    //extra methods db relatedOperations
    //custom query methods
    //custom finder methods

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    
}
