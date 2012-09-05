package com.mcorrigal.matchingEngine.orderBook;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class OrderBookImpl implements OrderBook {

	private OrderList bids;
	private OrderList asks;
	
	public OrderBookImpl(OrderList bids, OrderList asks) {
		this.bids = bids;
		this.asks = asks;
	}

	@Override
	public void newBuyOrder(BuyOrder order) {
		bids.add(order);
	}

	@Override
	public void newSellOrder(SellOrder order) {
		asks.add(order);
	}

	@Override
	public OrderBookSnapshot snapshot() {
		return new OrderBookSnapshot(bids, asks);
	}
	
}
