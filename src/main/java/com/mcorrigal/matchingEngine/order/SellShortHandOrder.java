package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.factories.OrderFactory;

public class SellShortHandOrder extends ShortHandOrder {

	public SellShortHandOrder(String shortHandOrderDescription) {
		super(shortHandOrderDescription);
	}

    @Override
    public SellOrder manufactureNewOrder() {
        return OrderFactory.newLimitSell(createNewOrderIdFromTime(), price, quantity);
    }

}
