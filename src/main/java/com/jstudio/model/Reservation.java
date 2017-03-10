package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {

	@Id
	@Column(name="idreservation")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idreservation;

	private int personid;
	private int flightid;

	public Reservation(){}

	public Reservation(int personid, int flightid) {
		super();
		this.personid = personid;
		this.flightid = flightid;
	}

	public int getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}

	@Override
	public String toString() {
		return "Reservation [idreservation=" + idreservation + ", personid=" + personid + ", flightid=" + flightid
				+ "]";
	}


}
