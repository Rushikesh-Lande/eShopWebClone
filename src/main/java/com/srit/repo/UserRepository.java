package com.srit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srit.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(final String p0);
    
    public User findByName(final String p0);
    
    @Query("select a from User a where a.employeeid=:employeeid")
    User findByEmployeeId(@Param("employeeid") final String p0);
    
    List<User> findAll();
}
