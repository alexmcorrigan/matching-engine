package com.mcorrigal.matchingEngine.utils;

import com.mcorrigal.matchingEngine.order.ShortHandOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide.OrderBookSideName;

public class OrderBookUtils {

	public static OrderBookSide orderBookSideFrom(String s) throws InstantiationException, IllegalAccessException {
		OrderBookSideName side = OrderBookSideName.valueOf(s.toUpperCase());
		return side.createOrderBookSide();
	}

    public static boolean orderListContainsOrder(OrderList orders, ShortHandOrder shortHandOrder) {
        boolean containsOrder = false;
        for (Order order : orders.getAll()) {
            containsOrder = order.toShortHandNotation().equals(shortHandOrder);
        }
        return containsOrder;
    }
	
}
