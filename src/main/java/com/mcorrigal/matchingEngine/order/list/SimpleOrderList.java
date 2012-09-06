package com.mcorrigal.matchingEngine.order.list;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean remove(Order order) {
        return orders.remove(order);
    }
}
