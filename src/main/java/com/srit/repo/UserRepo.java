package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Stock;
import com.srit.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
}
