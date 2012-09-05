package com.mcorrigal.matchingEngine;

public class MatchingEngineFactory {

	public static MatchingEngine newInstance() {
		OrderBookSide bids = OrderBookSideFactory.newBidOrderBookSide();
		OrderBookSide asks = OrderBookSideFactory.newAskOrderBookSide();
		OrderBook orderBook = new OrderBook(bids, asks);
		return new MatchingEngine(orderBook);
	}
	
}
