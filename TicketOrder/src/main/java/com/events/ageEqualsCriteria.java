package com.events;

import com.enities.Order;

public class ageEqualsCriteria implements Criteria
{
	public ageEqualsCriteria()
	{
	}

	@Override
	public boolean check(Object object , Order order) {
		int age = (int)object;
		return order.getVisitor().getAge() == age;

	}
}