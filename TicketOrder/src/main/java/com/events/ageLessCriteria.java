package com.events;

import com.enities.Order;

public class ageLessCriteria implements Criteria
{
	public ageLessCriteria()
	{
	}

	@Override
	public boolean check(Object object , Order order) {
		int age = (int)object;
		return order.getVisitor().getAge() < age;

	}
}