package com.parkingspot.db.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARKING_SPOT")
public class ParkingSpot {
	
	@Id
	@Basic(optional = false)
	private long id;
	
	@Basic(optional = false)
	@Column(name = "LATITUDE")
	private double latitude;
	
	@Basic(optional = false)
	@Column(name = "LONGITUDE")
	private double longitude;
	
	@Basic(optional = false)
	@Column(name = "NAME")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "ADDRESS")
	private String address;
	
	@OneToOne(optional = true, cascade=CascadeType.MERGE)
	@JoinColumn(name = "RESERVATION_ID")
	ReservationDetails resevationId;

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the resevationId
	 */
	public ReservationDetails getResevationId() {
		return resevationId;
	}

	/**
	 * @param resevationId the resevationId to set
	 */
	public void setResevationId(ReservationDetails resevationId) {
		this.resevationId = resevationId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
}
