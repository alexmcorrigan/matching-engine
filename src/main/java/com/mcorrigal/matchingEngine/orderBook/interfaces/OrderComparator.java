package com.mcorrigal.matchingEngine.orderBook.interfaces;

import java.util.Comparator;

import com.mcorrigal.matchingEngine.order.interfaces.Order;

public abstract class OrderComparator implements Comparator<Order> {

	@Override
	public abstract int compare(Order o1, Order o2);

}
