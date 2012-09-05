package com.mcorrigal.matchingEngine.order.interfaces;

import java.util.List;


public interface OrderList {
	
	public boolean isEmpty();
	public int size();
	public void add(Order order);
	public void addAll(OrderList orderList);
	public Order get(int i);
	public List<Order> getAll();
	
}
