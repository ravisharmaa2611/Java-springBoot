package com.xyalgo.bookmyshow.admin;

import com.xyalgo.bookmyshow.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetail implements UserDetailsService {

    @Autowired
    com.xyalgo.bookmyshow.admin.AdminRepo adminRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Admin admin = adminRepo.findByUsername(username).get();
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(admin.getUsername()).password(admin.getPassword()).authorities("ADMIN").build();

        return user;
    }


}
