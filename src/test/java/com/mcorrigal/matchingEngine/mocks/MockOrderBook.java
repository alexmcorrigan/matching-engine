package com.mcorrigal.matchingEngine.mocks;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class MockOrderBook implements OrderBook {

	private int bidCount = 0;
	private int askCount = 0;
	
	@Override
	public void newBuyOrder(BuyOrder order) {
		bidCount ++;
	}

	@Override
	public void newSellOrder(SellOrder order) {
		askCount ++;
	}

	@Override
	public OrderBookSnapshot snapshot() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBidCount() {
		return bidCount;
	}

	public int getAskCount() {
		return askCount;
	}

}
