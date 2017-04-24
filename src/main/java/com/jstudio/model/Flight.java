package com.jstudio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "idflight")
	private int idflight;
	private Date term;
	private String flight_number;
	private double cost;

	@ManyToOne
    @JoinColumn(name = "idrout")
	Rout rout;

	@OneToMany(mappedBy = "flight")
    private List<Reservation> reservation = new ArrayList<Reservation>();

	public Flight(){}

	public Flight(Date term, String flight_number, Rout rout, double cost){
		this.term = term;
		this.flight_number = flight_number;
		this.rout = rout;
		this.cost = cost;
	}

	public int getIdflight() {
		return idflight;
	}

	public void setIdflight(int idflight) {
		this.idflight = idflight;
	}

	public Date getTerm() {
		return term;
	}

	public void setTerm(Date term) {
		this.term = term;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public Rout getRout() {
		return rout;
	}

	public void setRout(Rout rout) {
		this.rout = rout;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Flight [idflight=" + idflight + ", term=" + term + ", flight_number=" + flight_number + ", routid="
				+ rout + ", cost=" + cost + "]";
	}

}
