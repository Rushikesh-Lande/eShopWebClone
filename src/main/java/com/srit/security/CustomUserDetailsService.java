// 
// Decompiled by Procyon v0.5.36
// 

package com.srit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srit.model.User;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private SecurityService securityService;
    
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        System.out.println("**********************************2************************************************");
        final User user = this.securityService.findUserByEmployeeId(userName);
        if (user == null) {
            throw new UsernameNotFoundException("InValid Credentials");
        }
        return (UserDetails)new AuthenticatedUser(user);
    }
}
