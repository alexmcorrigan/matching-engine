package com.mcorrigal.matchingEngine.features;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_ORDER_ID;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.OrderId;
import com.mcorrigal.matchingEngine.order.SellOrder;

public class SellShortHandOrder extends ShortHandOrder {

	public SellShortHandOrder(String shortHandOrderDescription) {
		super(shortHandOrderDescription);
	}

	@Override
	public SellOrder toOrder() {
		return OrderFactory.newLimitSell(OrderId.create(DUMMY_ORDER_ID), price, quantity);
	}
	
}
