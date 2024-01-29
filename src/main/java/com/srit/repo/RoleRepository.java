package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByName(final String p0);
}
