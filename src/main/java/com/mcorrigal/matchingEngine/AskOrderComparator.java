package com.mcorrigal.matchingEngine;


public class AskOrderComparator extends OrderComparator {

	public int compare(Order o1, Order o2) {
		Price p1 = o1.getPrice();
		Price p2 = o2.getPrice();
		
		if (p1.isGreaterThan(p2)) {
			return 1;
		} else if (p1.isLessThan(p2)) {
			return -1;
		} else {
			return 0;
		}
	}

}
