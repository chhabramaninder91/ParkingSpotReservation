package com.parkingspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkingspot.db.model.ReservationDetails;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, Long> {
	
	@Query("SELECT r from ReservationDetails r where r.userId = :userId")
	List<ReservationDetails>findByUserId(@Param("userId")long userId);
	
}
