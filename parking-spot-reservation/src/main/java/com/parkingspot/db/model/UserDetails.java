package com.parkingspot.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Basic(optional = false)
	private long id;

	@Basic(optional = false)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Basic(optional = true)
	@Column(name = "LAST_NAME")
	private String lastName;

}
