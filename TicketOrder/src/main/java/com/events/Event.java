package com.events;

import com.enities.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {
	private String condition;

	public Event(String condition){
		this.condition = condition;
	}

	public double reduce(Order order){
		 if(parse(order)){
		 	double result = order.getSession().getCost();
		 	String[] parsed = condition.split(",");
		 	switch(parsed[parsed.length - 1]){
				case "!":
					result -= Double.parseDouble(parsed[parsed.length - 2]);
					break;
				case "%":
					result -= (result*Double.parseDouble(parsed[parsed.length - 2]))/100;
					break;
					default:
						throw new IllegalArgumentException("The command is created wrong. Find the error in creating the command.");
			}
			return result;
		 }
		 return 0;
	}

	private static final Map<String , Criteria> conditions = new HashMap<String, Criteria>(){{
		put("AGE<" , new ageLessCriteria());
		put("AGE>" , new ageMoreCriteria());
		put("AGE==" , new ageEqualsCriteria());
	}};
	private boolean parse(Order order){
		String[] parsed = condition.split(",");
		String[] subCondition;
		Criteria criteria;
		boolean status = true;
		for(int i = 0 ; i < parsed.length ; i++){
			 subCondition = parsed[i].split(" ");
			 criteria = conditions.get(subCondition[0]);
			 if(criteria != null){
			 	status = criteria.check(Integer.parseInt(subCondition[1]) , order);
			 }
			 if(!status) return false;
		}
		return true;
	}


}