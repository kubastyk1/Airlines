package com.jstudio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rout")
public class Rout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idrout")
	private int idrout;

	@ManyToOne
	@JoinColumn(name = "idairport", insertable = false, updatable = false)
	Airport from_airport;

	@ManyToOne
	@JoinColumn(name = "idairport", insertable = false, updatable = false)
	Airport to_airport;

	@OneToMany(mappedBy = "rout")
    private List<Flight> flight = new ArrayList<Flight>();

	public Rout(){}

	public Rout(Airport from_airport, Airport to_airport){
		this.from_airport = from_airport;
		this.to_airport = to_airport;
	}

	public int getIdrout() {
		return idrout;
	}

	public void setIdrout(int idrout) {
		this.idrout = idrout;
	}

	public Airport getFrom_airport() {
		return from_airport;
	}

	public void setFrom_airport(Airport from_airport) {
		this.from_airport = from_airport;
	}

	public Airport getTo_airport() {
		return to_airport;
	}

	public void setTo_airport(Airport to_airport) {
		this.to_airport = to_airport;
	}

	@Override
	public String toString() {
		return "Rout [idrout=" + idrout + ", from_airport=" + from_airport + ", to_airport=" + to_airport + "]";
	}

}
