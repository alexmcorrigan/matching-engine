package com.mcorrigal.matchingEngine.orderBook.bid;

import com.mcorrigal.matchingEngine.order.Price;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderComparator;


public class BidOrderComparator extends OrderComparator {

	@Override
	public int compare(Order o1, Order o2) {
		Price p1 = o1.getPrice();
		Price p2 = o2.getPrice();
		
		if (p1.isGreaterThan(p2)) {
			return -1;
		} else if (p1.isLessThan(p2)) {
			return 1;
		} else {
			return 0;
		}
	}

}
