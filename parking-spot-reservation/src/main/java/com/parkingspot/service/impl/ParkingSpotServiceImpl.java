package com.parkingspot.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parkingspot.db.model.ParkingSpot;
import com.parkingspot.db.model.ReservationDetails;
import com.parkingspot.db.model.UserDetails;
import com.parkingspot.dto.ParkingSpotDto;
import com.parkingspot.dto.ReservationDto;
import com.parkingspot.repository.ParkingSpotRepository;
import com.parkingspot.repository.ReservationRepository;
import com.parkingspot.repository.UserRepository;
import com.parkingspot.service.ParkingSpotService;
import com.parkingspot.utils.ParkingSpotUtils;

@Component
public class ParkingSpotServiceImpl implements ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private ReservationRepository reservationrepository;
	

	@Override
	public List<ParkingSpotDto> getAvailableParkingSpots() {
		List<ParkingSpotDto> results = new ArrayList<>();
		List<ParkingSpot> parkingSpots = parkingSpotRepository.findAllAvailableSpots();
		parkingSpots.forEach(parkingSpot -> {
			ParkingSpotDto dto = new ParkingSpotDto();
			BeanUtils.copyProperties(parkingSpot, dto);
			results.add(dto);
		});

		return results;
	}
	
	/**
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	@Override
	public List<ParkingSpotDto> getParkingSlotsWithinRadius(double latitude, double longitude, double radius) {
		List<ParkingSpotDto> results = new ArrayList<>();
		double radLat = ParkingSpotUtils.deg2rad(latitude);
		double radLng = ParkingSpotUtils.deg2rad(longitude);
		radius = (radius/1609);
				/*dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  if (unit == 'K') {
		    dist = dist * 1.609344;
		  } else if (unit == 'N') {
		  dist = dist * 0.8684;
		    }*/
		double radDistance = ParkingSpotUtils.deg2rad(Math.cos(radius));
		List<ParkingSpot> parkingSpots  = parkingSpotRepository.findNearbySpotsWithinRadius(radLat, radLng, radDistance);
		parkingSpots.forEach(parkingSpot -> {
			ParkingSpotDto dto = new ParkingSpotDto();
			BeanUtils.copyProperties(parkingSpot, dto);
			results.add(dto);
		});
		
		return results;
		
	}
	
	@Override
	public String reserveParking(long userId, long parkingId, int durationInHrs) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingId);
		ReservationDetails rd = new ReservationDetails();
		Random r = new Random();
		rd.setName("RES" + r.nextInt());
		UserDetails user = userRepository.findById(userId);
		rd.setUserId(user);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, durationInHrs);
		Timestamp ts = new Timestamp(calendar.getTime().getTime());
		rd.setEndTime(ts);
		rd.setStartTime(new Timestamp(new Date().getTime()));

		parkingSpot.setResevationId(rd);
		parkingSpotRepository.save(parkingSpot);
		return "Parking reserved successfully";
	}
	
	@Override
	public List<ReservationDto> getExistingReservationsForUser(long userId) {
		List<ReservationDto> results = new ArrayList<>();
		List<ReservationDetails> reservations = reservationrepository.findByUserId(userId);
		reservations.forEach(reservation -> {
			ReservationDto dto = new ReservationDto();
			BeanUtils.copyProperties(reservation, dto);
			results.add(dto);
		});
		
		return results;
	}
	
	@Override
	public String cancelReservation(long reservationId) {
		Optional<ReservationDetails> reservation = reservationrepository.findById(reservationId);
		if (reservation.get() != null)
			reservationrepository.delete(reservation.get());
		else 
			 return "No such reservation exists";
		return "Parking reserved successfully";
	}
	
}
