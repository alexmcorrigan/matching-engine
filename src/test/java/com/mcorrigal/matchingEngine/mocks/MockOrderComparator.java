package com.mcorrigal.matchingEngine.mocks;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderComparator;

public class MockOrderComparator extends OrderComparator {

	@Override
	public int compare(Order o1, Order o2) {
		return 0;
	}

}
