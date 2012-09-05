package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public class BuyOrder extends Order {

	public BuyOrder(OrderId id, OrderType type, Price price, Quantity quantity) {
		super(id, type, OrderSide.BUY, price, quantity);
	}

	@Override
	public void work(OrderBook orderBook) {
		orderBook.newBuyOrder(this);
	}
	
}