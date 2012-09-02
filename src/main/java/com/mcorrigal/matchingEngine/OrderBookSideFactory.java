package com.mcorrigal.matchingEngine;

public class OrderBookSideFactory {

	public static OrderBookSide newBidOrderBookSide() {
		return new OrderBookSide(new BidOrderComparator());
	}
	
	public static OrderBookSide newAskOrderBookSide() {
		return new OrderBookSide(new AskOrderComparator());
	}
	
}
