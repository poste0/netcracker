package com.events;

import com.enities.*;

public class Main
{
	public Main()
	{
	}

	public static void main(String[] args)
	{
		Event event = new Event("AGE> 20,AGE< 38,5,!");
		Order order = new Booking(new Session(null , null , null , 1500) , new Visitor("sadasd" , "sdasd" , 35) , 0);
		System.out.println(event.reduce(order));
	}
}