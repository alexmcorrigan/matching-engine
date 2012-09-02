package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MatchingEngineTest {

	private OrderBookSide bids;
	private OrderBookSide asks;
	private OrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	@Before
	public void setUp() {
		bids = OrderBookSideFactory.newBidOrderBookSide();
		asks = OrderBookSideFactory.newAskOrderBookSide();
		orderBook = new OrderBook(bids, asks);
		matchingEngine = new MatchingEngine(orderBook);
	}
	
	@Test
	public void receiveNewBuyOrder() {
		matchingEngine.newOrder(DUMMY_LIMIT_BUY_ORDER);
		assertThat(bids.getAllOrders().size(), is(1));
		assertThat(asks.getAllOrders().size(), is(0));
	}
	
	@Test
	public void receiveNewSellOrder() {
		matchingEngine.newOrder(DUMMY_LIMIT_SELL_ORDER);
		assertThat(bids.getAllOrders().size(), is(0));
		assertThat(asks.getAllOrders().size(), is(1));
	}
	
}
