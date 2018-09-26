package com.enities;

import java.util.UUID;

public class Booking extends Order
{
	public Booking() {
	}

	public Booking(Session session , Visitor visitor , int ticketsAmount){
		super(session , visitor , ticketsAmount);
	}
}