package com.parkingspot.db.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION_DETAILS")
public class ReservationDetails {
	@Id
	@Basic(optional = false)
	private long id;
	
	@Basic(optional = false)
	@Column(name = "UNIQUE_NAME")
	private String name;
	
	@Column(name="STARTTIME")
	private Timestamp startTime;
	
	@Column(name="ENDTIME")
	private Timestamp endTime;
	
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	@ManyToOne(optional = true)
	private UserDetails userId;
	
	@Column(name="ACTIVE")
	private boolean active;

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
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the userId
	 */
	public UserDetails getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
