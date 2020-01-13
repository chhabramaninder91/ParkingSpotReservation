package com.parkingspot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkingspot.db.model.ParkingSpot;
import com.parkingspot.repository.ParkingSpotRepository;
import com.parkingspot.service.impl.ParkingSpotServiceImpl;

@ExtendWith(MockitoExtension.class)
class ParkingSpotServiceTest {
	
	@InjectMocks
	private ParkingSpotServiceImpl parkingSpotService;
	@Mock
	private ParkingSpotRepository parkingSpotRepository;
	
	
	@Test
	void testAvailableParkingSpots(){
		List<ParkingSpot> parkings = new ArrayList<>();
		ParkingSpot spot = new ParkingSpot();
		spot.setAddress("Aundh");
		spot.setName("Parking 1");
		parkings.add(spot);
		when(parkingSpotRepository.findAllAvailableSpots()).thenReturn(parkings);
		assertEquals(1, parkingSpotService.getAvailableParkingSpots().size());
	}
	
	@Test
	void testAvailableParkingSpotsEmpty(){
		
		when(parkingSpotRepository.findAllAvailableSpots()).thenReturn(null);
		assertEquals(0, parkingSpotService.getAvailableParkingSpots().size());
	}
	
	
	@Test
	void testParkingSpotsEmpty(){
		
		when(parkingSpotRepository.findAllAvailableSpots()).thenReturn(null);
		assertEquals(0, parkingSpotService.getAvailableParkingSpots().size());
	}

}
