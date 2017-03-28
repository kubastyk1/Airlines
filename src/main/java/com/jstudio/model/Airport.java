package com.jstudio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "airport")
public class Airport {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "idairport")
	private int idairport;

	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "idairport2")
	private int idairport2;

	private String city;
	private String code;

	@OneToMany(mappedBy = "from_airport")
    private List<Rout> rout = new ArrayList<Rout>();

	@OneToMany(mappedBy = "to_airport")
    private List<Rout> rout2 = new ArrayList<Rout>();

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

	public String getClassName(){
		return "Airport";
	}

}
