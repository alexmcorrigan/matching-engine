package com.mcorrigal.matchingEngine.features.SpecificationModels;

import com.mcorrigal.matchingEngine.order.BuyShortHandOrder;
import com.mcorrigal.matchingEngine.order.SellShortHandOrder;
import com.mcorrigal.matchingEngine.order.ShortHandOrder;


public class SpecifiedOrderBookLevel {

	private String bid = null;
	private String ask = null;
	
	public BuyShortHandOrder getBidSideAsShortHandDescription() {
		return bid.isEmpty() ? null : ShortHandOrder.createBuy(bid);
	}
	public SellShortHandOrder getAskSideAsShortHandDescription() {
		return ask.isEmpty() ? null : ShortHandOrder.createSell(ask);
	}

	
	
}
