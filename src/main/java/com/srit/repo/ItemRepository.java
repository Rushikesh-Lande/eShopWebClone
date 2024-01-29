package com.srit.repo;

import com.srit.model.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByItemNameContainingIgnoreCase(String keyword);

	Optional<Item> findByImageLink(String imageLink);
}
