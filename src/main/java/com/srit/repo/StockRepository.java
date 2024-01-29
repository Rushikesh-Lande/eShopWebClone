package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
