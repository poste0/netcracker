package com.enities;

public class Purchasing extends Order
{

	public Purchasing(){}

	public Purchasing(Session session , Visitor visitor , int ticketsAmount){
		super(session , visitor , ticketsAmount);
	}
}