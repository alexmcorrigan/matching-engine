package com.mcorrigal.matchingEngine.features;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_ORDER_ID;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.OrderId;

public class BuyShortHandOrder extends ShortHandOrder {

	public BuyShortHandOrder(String shortHandOrderDescription) {
		super(shortHandOrderDescription);
	}

	@Override
	public BuyOrder toOrder() {
		return OrderFactory.newLimitBuy(OrderId.create(DUMMY_ORDER_ID), price, quantity);
	}
	
}
