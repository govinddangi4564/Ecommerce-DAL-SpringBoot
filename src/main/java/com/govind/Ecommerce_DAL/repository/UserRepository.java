package com.govind.Ecommerce_DAL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.govind.Ecommerce_DAL.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable pageable);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	@Query("""
			    SELECT u
			    FROM User u
			    WHERE LOWER(u.customerProfile.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(u.customerProfile.phoneNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(u.customerProfile.address) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	List<User> searchUsers(@Param("keyword") String keyword);
}
