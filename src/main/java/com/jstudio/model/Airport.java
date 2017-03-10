package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idairport")
	private int idairport;
	private String city;
	private String code;

	public Airport(){}

	public Airport(String city, String code){
		this.city = city;
		this.code = code;
	}

	public int getIdairport() {
		return idairport;
	}

	public void setIdairport(int idairport) {
		this.idairport = idairport;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Airport [idairport=" + idairport + ", city=" + city + ", code=" + code + "]";
	}

}
