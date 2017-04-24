package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="reservation")
public class Reservation {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="idreservation")
	private int idreservation;

	@ManyToOne
	@JoinColumn(name = "idflight")
	Flight flight;

	@ManyToOne
	@JoinColumn(name = "username")
	User username;

	public Reservation(){}

	public Reservation(User user, Flight flight) {
		super();
		this.username = user;
		this.flight = flight;
	}

	public int getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getUser() {
		return username;
	}

	public void setUser(User user) {
		this.username = user;
	}

	@Override
	public String toString() {
		return "Reservation [idreservation=" + idreservation + ", " + (flight != null ? "flight=" + flight + ", " : "")
				+ (username != null ? "user=" + username : "") + "]";
	}


}
