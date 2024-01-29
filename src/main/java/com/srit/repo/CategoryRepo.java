package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.srit.model.Category;
// Category is nothing but jpa entity second parameter is primary key long of entity class
public interface CategoryRepo extends JpaRepository<Category, Long>{
//                                     type of jpa entity & type of primary key

}
