package com.parkingspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkingspot.db.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
	
	@Query("SELECT u from UserDetails u where u.id = :userId")
	UserDetails findById(@Param("userId")long userId);

}
