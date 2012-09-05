package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;

public class TestConstants {

	public static final String DUMMY_ORDER_ID = "100001";
	public static final String DUMMY_PRICE = "100.99";
	public static final String DUMMY_QUANTITY = "200";
	public static final BuyOrder DUMMY_LIMIT_BUY_ORDER = dummyLimitBuyForPrice(DUMMY_PRICE);
	public static final SellOrder DUMMY_LIMIT_SELL_ORDER = dummyLimitSellForPrice(DUMMY_PRICE);
	
	public static BuyOrder dummyLimitBuyForPrice(String price) {
		return OrderFactory.newLimitBuy(DUMMY_ORDER_ID, price, DUMMY_QUANTITY); 
	}
	
	public static SellOrder dummyLimitSellForPrice(String price) {
		return OrderFactory.newLimitSell(DUMMY_ORDER_ID, price, DUMMY_QUANTITY); 
	}
	
}