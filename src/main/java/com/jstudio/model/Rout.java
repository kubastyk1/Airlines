package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rout")
public class Rout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idrout")
	private int idrout;
	private int from_airport;
	private int to_airport;

	public Rout(){}

	public Rout(int from_airport, int to_airport){
		this.from_airport = from_airport;
		this.to_airport = to_airport;
	}

	public int getIdrout() {
		return idrout;
	}

	public void setIdrout(int idrout) {
		this.idrout = idrout;
	}

	public int getFrom_airport() {
		return from_airport;
	}

	public void setFrom_airport(int from_airport) {
		this.from_airport = from_airport;
	}

	public int getTo_airport() {
		return to_airport;
	}

	public void setTo_airport(int to_airport) {
		this.to_airport = to_airport;
	}

	@Override
	public String toString() {
		return "Rout [idrout=" + idrout + ", from_airport=" + from_airport + ", to_airport=" + to_airport + "]";
	}

}
