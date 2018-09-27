package com.enities;

import com.enities.users.Visitor;

public class Booking extends Order
{
	public Booking() {
	}

	public Booking(Session session , Visitor visitor , int ticketsAmount){
		super(session , visitor , ticketsAmount);
	}
}