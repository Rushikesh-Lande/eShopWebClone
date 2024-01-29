package com.srit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srit.pojo.CartItems;

public interface CartRepository extends JpaRepository<CartItems, Long> {

	Optional<CartItems> findByImageLink(String imageLink);
}
