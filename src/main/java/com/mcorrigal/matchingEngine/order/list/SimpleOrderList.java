package com.mcorrigal.matchingEngine.order.list;

import java.util.ArrayList;
import java.util.List;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;

public class SimpleOrderList implements OrderList {

	protected List<Order> orders = new ArrayList<Order>();
	
	@Override
	public boolean isEmpty() {
		return orders.size() == 0;
	}
	
	@Override
	public void add(Order order) {
		orders.add(order);
	}
	
	@Override
	public void addAll(OrderList orderList) {
		for (Order order : orderList.getAll()) {
			add(order);
		}
	}
	
	@Override
	public Order get(int i) {
		return orders.get(i);
	}
	
	@Override
	public List<Order> getAll() {
		return orders;
	}

	@Override
	public int size() {
		return orders.size();
	}
	
}
