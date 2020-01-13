package com.parkingspot.service;

import java.util.List;

import com.parkingspot.dto.ParkingSpotDto;
import com.parkingspot.dto.ReservationDto;

public interface ParkingSpotService {

	List<ParkingSpotDto> getAvailableParkingSpots();
	List<ParkingSpotDto> getParkingSlotsWithinRadius(double latitude, double longitude, double radius);
	String reserveParking(long userId, long parkingId, int durationInHrs);
	List<ReservationDto> getExistingReservationsForUser(long userId);
	String cancelReservation(long reservationId);
}
