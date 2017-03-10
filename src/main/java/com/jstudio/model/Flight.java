package com.jstudio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idflight")
	private int idflight;
	private Date term;
	private String flight_number;
	private int routid;
	private double cost;

	public Flight(){}

	public Flight(Date term, String flight_number, int routid, double cost){
		this.term = term;
		this.flight_number = flight_number;
		this.routid = routid;
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

	public int getRoutid() {
		return routid;
	}

	public void setRoutid(int routid) {
		this.routid = routid;
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
				+ routid + ", cost=" + cost + "]";
	}

}
