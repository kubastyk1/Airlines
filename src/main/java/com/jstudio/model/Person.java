package com.jstudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="idperson")
	private int idperson;

	private String login;
	private String email;
	private String password;
	private String name;
	private String surname;

	public Person(){}

	public Person(String login, String email, String password, String name, String surname) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public int getIdperson() {
		return idperson;
	}

	public void setIdperson(int idperson) {
		this.idperson = idperson;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public String toString() {
		return "Person [idperson=" + idperson + ", " + (login != null ? "login=" + login + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (surname != null ? "surname=" + surname : "") + "]";
	}


}
