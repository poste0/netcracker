package com.enities;

import java.util.UUID;

public class Hall {
	public Hall() {
	}

	private UUID id;

	private double capacity;

	public Hall(double capacity) {
		this.capacity = capacity;
		id = UUID.randomUUID();
	}

	public double getCapacity()
	{
		return capacity;
	}

	public void setCapacity(double capacity)
	{
		this.capacity = capacity;
	}

	public UUID getId()
	{
		return id;
	}
	
}