// 
// Decompiled by Procyon v0.5.36
// 

package com.srit.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.srit.model.Permission;
import com.srit.model.Role;

public class AuthenticatedUser extends User
{
    private static final long serialVersionUID = -3216749761267953616L;
    private com.srit.model.User user;
    
    public AuthenticatedUser(final com.srit.model.User user) {
        super(user.getEmail(), user.getPassword(), (Collection)getAuthorities(user));
        this.user = user;
    }
    
    public com.srit.model.User getUser() {
        return this.user;
    }
    
    private static Collection<? extends GrantedAuthority> getAuthorities(final com.srit.model.User user) {
        final Set<String> roleAndPermissions = new HashSet<String>();
        final List<Role> roles = (List<Role>)user.getRoles();
        for (final Role role : roles) {
            roleAndPermissions.add(role.getName());
            final List<Permission> permissions = (List<Permission>)role.getPermissions();
            for (final Permission permission : permissions) {
                roleAndPermissions.add("ROLE_" + permission.getName());
            }
            roleAndPermissions.add("ROLE_USER"); 
        }
        final String[] roleNames = new String[roleAndPermissions.size()];
        final Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)AuthorityUtils.createAuthorityList((String[])roleAndPermissions.toArray(roleNames));
        return authorities;
    }
}
