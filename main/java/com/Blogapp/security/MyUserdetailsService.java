package com.Blogapp.security;

import com.Blogapp.entity.Role;
import com.Blogapp.entity.User;
import com.Blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserdetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user= userRepository.findByUsernameOrEmail(username,username).orElseThrow(
                 ()->new UsernameNotFoundException("user not found with this email or username"+username)
         );

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),maptoAuthenticated(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> maptoAuthenticated( Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
