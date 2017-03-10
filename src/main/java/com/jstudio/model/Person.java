package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name="person")
public class Person {

	@Id
	@Column(name="idperson")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idperson;

	private String name;
	private String surname;
	private int pesel;

	public Person(){}

	public Person(String name, String surname, int pesel) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}

	public int getPesel() {
		return pesel;
	}

	public void setPesel(int pesel) {
		this.pesel = pesel;
	}

	public int getId() {
		return idperson;
	}

	public void setId(int idperson) {
		this.idperson = idperson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString(){
		return "id="+idperson+", name="+name+", surname="+surname;
	}
}
