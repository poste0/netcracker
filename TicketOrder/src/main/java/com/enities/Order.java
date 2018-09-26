package com.enities;
import java.util.UUID;

public abstract class Order {
	private UUID id;

	private Visitor visitor;

	private Session session;

	private int ticketsAmount;

	public  Order(Session session , Visitor visitor , int ticketsAmount){
		id = UUID.randomUUID();
		this.session = session;
		this.visitor = visitor;
		this.ticketsAmount = ticketsAmount;
		
	}

	public Order(){}

	public int getTicketsAmount()
	{
		return ticketsAmount;
	}

	public void setTicketsAmount(int ticketsAmount)
	{
		this.ticketsAmount = ticketsAmount;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public Visitor getVisitor()
	{
		return visitor;
	}

	public void setVisitor(Visitor visitor)
	{
		this.visitor = visitor;
	}

	public Session getSession()
	{
		return session;
	}

	public void setSession(Session session)
	{
		this.session = session;
	}
}