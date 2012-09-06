package com.mcorrigal.matchingEngine.orderBook.ask;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderComparator;


public class AskOrderComparator extends OrderComparator {

	@Override
	public int compare(Order o1, Order o2) {
        if (o1.isMoreExpensive(o2)) {
			return 1;
        } else if (o1.isChepaer(o2)) {
			return -1;
		} else {
			return 0;
		}
	}

}
