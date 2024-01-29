package com.srit.repo;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import com.srit.model.User;
@Repository
public interface CustomerCrudRepository extends UpiCommonDao,  UserDetailsService
{
   
    
    User getByEmployeeId(final String p0);
    
  
}
