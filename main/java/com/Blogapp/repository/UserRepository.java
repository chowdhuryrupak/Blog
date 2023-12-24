package com.Blogapp.repository;

import com.Blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Example;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    Optional<User>findByUsername(String username);
//
//    Optional<User> findByUsernameOrEmail(String email,String username);
//    Optional<User> findByname(String name);
//
//    Boolean exitsByUsername(String username);
//    Boolean exitsByEmail(String email );
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
