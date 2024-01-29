package com.srit.security;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.srit.model.Permission;
import com.srit.model.Role;
import com.srit.model.User;
import com.srit.repo.PermissionRepository;
import com.srit.repo.RoleRepository;
import com.srit.repo.UserRepository;

@Service
@Transactional
public class SecurityService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;
    
    public User findUserByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }
    
    public User findUserByEmployeeId(final String employeeid) {
        System.out.println("**********************************3************************************************");
        return this.userRepository.findByEmployeeId(employeeid);
    }
    
    public User findUserByName(final String name) {
        return this.userRepository.findByName(name);
    }
    
    public String resetPassword(final String email) {
        final User user = this.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email address");
        }
        final String uuid = UUID.randomUUID().toString();
        user.setPasswordResetToken(uuid);
        return uuid;
    }
    
    public void updatePassword(final String email, final String token, final String password) {
        final User user = this.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email address");
        }
        if (!StringUtils.hasText(token) || !token.equals(user.getPasswordResetToken())) {
            throw new UsernameNotFoundException("Invalid password reset token");
        }
        user.setPassword(password);
        user.setPasswordResetToken(null);
    }
    
    public boolean verifyPasswordResetToken(final String email, final String token) {
        final User user = this.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email address");
        }
        return StringUtils.hasText(token) && token.equals(user.getPasswordResetToken());
    }
    
    public List<Permission> getAllPermissions() {
        return (List<Permission>)this.permissionRepository.findAll();
    }
    
    public List<Role> getAllRoles() {
        return (List<Role>)this.roleRepository.findAll();
    }
    
    public Role getRoleByName(final String roleName) {
        return this.roleRepository.findByName(roleName);
    }
    
    public Role createRole(final Role role) {
        final Role roleByName = this.getRoleByName(role.getName());
        if (roleByName != null) {
            throw new UsernameNotFoundException("Role " + role.getName() + " already exist");
        }
        final List<Permission> persistedPermissions = new ArrayList<Permission>();
        final List<Permission> permissions = role.getPermissions();
        if (permissions != null) {
            for (final Permission permission : permissions) {
                if (permission.getId() != null) {
                    persistedPermissions.add(this.permissionRepository.findById(permission.getId()).get());
                }
            }
        }
        role.setPermissions(persistedPermissions);
        return (Role)this.roleRepository.save(role);
    }
    
    public Role updateRole(final Role role) {
        final Role persistedRole = this.getRoleById(role.getId());
        if (persistedRole == null) {
            throw new UsernameNotFoundException("Role " + role.getId() + " doesn't exist");
        }
        persistedRole.setDescription(role.getDescription());
        final List<Permission> updatedPermissions = new ArrayList<Permission>();
        final List<Permission> permissions = role.getPermissions();
        if (permissions != null) {
            for (final Permission permission : permissions) {
                if (permission.getId() != null) {
                    updatedPermissions.add((Permission)this.permissionRepository.findById(permission.getId()).get());
                }
            }
        }
        persistedRole.setPermissions(updatedPermissions);
        return (Role)this.roleRepository.save(persistedRole);
    }
    
    public Role getRoleById(final Integer id) {
        return (Role)this.roleRepository.findById(id).get();
    }
    
    public User getUserById(final Integer id) {
        return (User)this.userRepository.findById(id).get();
    }
    
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    
    public User createUser(final User user) {
        final User userByEmail = this.findUserByEmail(user.getEmail());
        if (userByEmail != null) {
            throw new UsernameNotFoundException("Email " + user.getEmail() + " already in use");
        }
        final List<Role> persistedRoles = new ArrayList<Role>();
        final List<Role> roles = user.getRoles();
        if (roles != null) {
            for (final Role role : roles) {
                if (role.getId() != null) {
                    persistedRoles.add((Role)this.roleRepository.findById(role.getId()).get());
                }
            }
        }
        user.setRoles(persistedRoles);
        return (User)this.userRepository.save(user);
    }
    
    public User updateUser(final User user) {
        final User persistedUser = this.getUserById(user.getId());
        if (persistedUser == null) {
            throw new UsernameNotFoundException("User " + user.getId() + " doesn't exist");
        }
        final List<Role> updatedRoles = new ArrayList<Role>();
        final List<Role> roles = user.getRoles();
        if (roles != null) {
            for (final Role role : roles) {
                if (role.getId() != null) {
                    updatedRoles.add((Role)this.roleRepository.findById(role.getId()).get());
                }
            }
        }
        persistedUser.setRoles(updatedRoles);
        persistedUser.setName(user.getName());
        persistedUser.setEmail(user.getEmail());
        persistedUser.setEmployeeid(user.getEmployeeid());
        return (User)this.userRepository.save(persistedUser);
    }
    
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
