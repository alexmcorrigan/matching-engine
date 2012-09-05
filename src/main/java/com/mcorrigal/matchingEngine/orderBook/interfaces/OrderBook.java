package com.mcorrigal.matchingEngine.orderBook.interfaces;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;

public interface OrderBook {

	public void newBuyOrder(BuyOrder order);
	public void newSellOrder(SellOrder order);
	public OrderBookSnapshot snapshot();
	
}
