package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>
{
}
