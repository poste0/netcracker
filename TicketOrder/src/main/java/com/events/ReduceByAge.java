package com.events;

import com.enities.Order;
import com.enities.Visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * The class is an event for the visitor's age.
 */
public class ReduceByAge extends Eventt
{
	public ReduceByAge()
	{
	}

	public ReduceByAge(String conditions){
		super(conditions);
	}

	private static final List<String> symbols = new LinkedList<String>(){{
		add("<");
		add(">");
		add("=");
		add("<=");
		add(">=");
		add("!=");
	}};
	protected static final String LESS = "<";
	protected static final String MORE = ">";
	protected static final String EQUALS = "=";
	protected static final String LESSOREQUALS = "<=";
	protected static final String MOREOREQUALS = ">=";
	protected static final String NOTEQUALS = "!+";
	public double reduce(Order order , double startPrice) {
		final int age = order.getVisitor().getAge();
		String[] parsedCondition = condition.split(",");
		if(parsedCondition[0].length() > 2) throw new IllegalArgumentException("The command is created wrong. Find the error in creating the command.");
		double result;
		switch (parsedCondition[0]){
			case LESS:
				result = age < Double.parseDouble(parsedCondition[1]) ? parse().reduce(startPrice) : startPrice;
				return result >= 0 ? result : 0;
			case MORE:
				result = age > Double.parseDouble(parsedCondition[1]) ? parse().reduce(Double.parseDouble(parsedCondition[2])) : startPrice;
				return result >= 0 ? result : 0;
			case EQUALS:
				result = age == Double.parseDouble(parsedCondition[1]) ? parse().reduce(Double.parseDouble(parsedCondition[2])) : startPrice;
				return result >= 0 ? result : 0;
			case LESSOREQUALS:
				result = age <= Double.parseDouble(parsedCondition[1]) ? parse().reduce(Double.parseDouble(parsedCondition[2])) : startPrice;
				return result >= 0 ? result : 0;
			case MOREOREQUALS:
				result = age >= Double.parseDouble(parsedCondition[1]) ? parse().reduce(Double.parseDouble(parsedCondition[2])) : startPrice;
				return result >= 0 ? result : 0;
			case NOTEQUALS:
				result = age != Double.parseDouble(parsedCondition[1]) ? parse().reduce(Double.parseDouble(parsedCondition[2])) : startPrice;
				return result >= 0 ? result : 0;
				default:
					throw new IllegalArgumentException("The command is created wrong. Find the error in creating the command.");

		}

	}

}