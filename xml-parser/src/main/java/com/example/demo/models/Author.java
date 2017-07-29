package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Author {
	private String surname;
	private String given;
	private String email;
	private List<Integer> affils = new ArrayList<Integer>();

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getAffils() {
		return affils;
	}

	public void setAffils(List<Integer> affils) {
		this.affils = affils;
	}

}
