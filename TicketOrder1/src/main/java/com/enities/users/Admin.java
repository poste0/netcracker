package com.enities.users;

import com.enities.Hall;

import java.util.Date;
import java.util.UUID;

public class Admin {
	public Admin() {
	}

	private UUID id;

	private String name;

	private Date hireDate;

	private int age;

	public Admin(String name , Date hireDate , int age){
		this.age = age;
		this.name = name;
		this.hireDate = hireDate;
		id = UUID.randomUUID();
	}

	public void createHall(double capacity){
		Hall hall = new Hall(capacity);
		//TODO Insert into the database
	}
}