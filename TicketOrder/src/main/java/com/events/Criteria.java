package com.events;

import com.enities.Order;

public interface Criteria {

	boolean check(Object object , Order order);
}