package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.factories.OrderFactory;

public class BuyShortHandOrder extends ShortHandOrder {

	public BuyShortHandOrder(String shortHandOrderDescription) {
		super(shortHandOrderDescription);
	}

    @Override
    public BuyOrder manufactureNewOrder() {
        return OrderFactory.newLimitBuy(createNewOrderIdFromTime(), price, quantity);
    }

}
