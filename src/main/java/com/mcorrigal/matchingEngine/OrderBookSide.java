package com.mcorrigal.matchingEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderBookSide {

	public static enum OrderBookSideType {BID, ASK}
	
	private OrderComparator orderComparator;
	private List<Order> orders = new ArrayList<Order>();
	
	public OrderBookSide(OrderComparator orderComparator) {
		this.orderComparator = orderComparator;
	}
	
	public boolean isEmpty() {
		return orders.size() == 0;
	}
	
	public void rest(Order order) {
		orders.add(order);
		prioritiseOrders();
	}
	
	public List<Order> getAllOrders() {
		return orders;
	}
	
	private void prioritiseOrders() {
		Collections.sort(orders, orderComparator);
	}
	
}
