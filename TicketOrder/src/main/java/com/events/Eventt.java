package com.events;

import com.enities.Order;
import com.enities.Visitor;

import java.util.List;

/**
 * This class has 1 abstract method that change the ticket price via that specific.
 * Method parse parses just 1 symbol that the same for any event. ! - reduce the cost bythe value. % - reduce the value by the percent.
 * The command structure: condition,condition border(1 or more),value,! || % Example: <,25,1500,!
 * TODO Now we can enter more than 100 percent. Change it.
 */
public abstract class Eventt {
	public Eventt()
	{
	}

	protected String condition;

	public Eventt(String condition){
		this.condition = condition;
	}

	public abstract double reduce(Order order , double startPrice);

	protected Action parse(){
		final String[] splittedParams = condition.split(",");
		return new Action() {
			public double reduce(double startPrice) {
				double result = startPrice;
				result -=  splittedParams[splittedParams.length - 1].equals("!") ?  Double.parseDouble(splittedParams[2]) :  (startPrice * Double.valueOf(splittedParams[2]))/100;
				return result;

			}
		};
	}

	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String condition)
	{
		this.condition = condition;
	}
}