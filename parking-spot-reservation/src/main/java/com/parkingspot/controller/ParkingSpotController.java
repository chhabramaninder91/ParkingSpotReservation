package com.parkingspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkingspot.dto.ParkingMessage;
import com.parkingspot.dto.ParkingSpotDto;
import com.parkingspot.dto.ReservationDto;
import com.parkingspot.service.ParkingSpotService;

@RestController
@RequestMapping("api/v1")
public class ParkingSpotController {

	@Autowired
	private ParkingSpotService parkingSpotService;
	
	@GetMapping(value ="/availableParkingSpots", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getParkingSpots() {
		List<ParkingSpotDto> availableParkingSpots = parkingSpotService.getAvailableParkingSpots();
		if (!CollectionUtils.isEmpty(availableParkingSpots))
			return new ResponseEntity<>(availableParkingSpots, HttpStatus.OK);
		return new ResponseEntity<String>("Parking spot not found", HttpStatus.OK);
		
	}
	
	@GetMapping(value ="/nearestParkingSpots", produces = MediaType.APPLICATION_JSON_VALUE, params={"lat", "lng", "radius"})
	public ResponseEntity<?> getNearestParkingSpots(@RequestParam("lat") double lat, @RequestParam("lng") double lng, 
			@RequestParam("radius") double radius) {
		List<ParkingSpotDto> availableParkingSpots = parkingSpotService.getParkingSlotsWithinRadius(lat, lng, radius);
		if (!CollectionUtils.isEmpty(availableParkingSpots))
			return new ResponseEntity<>(availableParkingSpots, HttpStatus.OK);
		return new ResponseEntity<>(new ParkingMessage("Parking not found"), HttpStatus.OK);
		
	}
	
	@PostMapping(value ="/reserveParking/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, params={"parkingId", "duration"})
	public ResponseEntity<?> reserveParking(@PathVariable("userId") long userId, @RequestParam("parkingId") long parkingId, 
			@RequestParam("duration") int duration) {
		String message = parkingSpotService.reserveParking(userId, parkingId, duration);
		if (!StringUtils.isEmpty(message))
			return new ResponseEntity<>(new ParkingMessage(message), HttpStatus.OK);
		return new ResponseEntity<>(new ParkingMessage("Parking not found"), HttpStatus.OK);
		
	} 
	
	@GetMapping(value ="/getReservations/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getParkingSpots(@PathVariable("userId")long userId) {
		List<ReservationDto> availableParkingSpots = parkingSpotService.getExistingReservationsForUser(userId);
		if (!CollectionUtils.isEmpty(availableParkingSpots))
			return new ResponseEntity<>(availableParkingSpots, HttpStatus.OK);
		return new ResponseEntity<String>("No Reservations found for the given user", HttpStatus.OK);
		
	}
	
	
	
}
