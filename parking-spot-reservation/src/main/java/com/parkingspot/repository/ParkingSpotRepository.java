package com.parkingspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkingspot.db.model.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
	
	@Query("SELECT p FROM ParkingSpot p Where p.resevationId is null")
	List<ParkingSpot> findAllAvailableSpots();
	
//	Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	
	@Query(value = "SELECT * from PARKING_SPOT where sin(:lat) * sin(LATITUDE) + cos(:lat) * cos(LATITUDE) * cos(LONGITUDE - :lng) <= :radius"
			+ " and RESERVATION_ID is null", nativeQuery = true)
	List<ParkingSpot> findNearbySpotsWithinRadius(@Param("lat")double lat, @Param("lng")double lng, @Param("radius")double radius);
	
	@Query("SELECT p from ParkingSpot p where p.id = :parkingId")
	ParkingSpot findById(@Param("parkingId")long parkingId);

}
