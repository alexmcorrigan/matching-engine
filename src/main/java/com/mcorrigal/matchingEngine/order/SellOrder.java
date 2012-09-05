package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public class SellOrder extends Order {

	public SellOrder(OrderId id, OrderType type, Price price, Quantity quantity) {
		super(id, type, OrderSide.SELL, price, quantity);
	}

	@Override
	public void work(OrderBook orderBook) {
		orderBook.newSellOrder(this);
	}
	
}
