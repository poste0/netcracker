package com.events;

import com.enities.Order;

public class ageMoreCriteria implements Criteria {
	public ageMoreCriteria()
	{
	}

	@Override
	public boolean check(Object object , Order order) {
		int age = (int)object;
		return order.getVisitor().getAge() > age;

	}
}