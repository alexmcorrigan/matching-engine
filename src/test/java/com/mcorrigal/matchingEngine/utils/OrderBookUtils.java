package com.mcorrigal.matchingEngine.utils;

import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide.OrderBookSideName;

public class OrderBookUtils {

	public static OrderBookSide orderBookSideFrom(String s) throws InstantiationException, IllegalAccessException {
		OrderBookSideName side = OrderBookSideName.valueOf(s.toUpperCase());
		return side.createOrderBookSide();
	}
	
}
