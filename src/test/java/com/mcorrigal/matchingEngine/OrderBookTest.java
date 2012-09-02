package com.mcorrigal.matchingEngine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.mcorrigal.matchingEngine.TestConstants.*;

import org.junit.Before;
import org.junit.Test;

public class OrderBookTest {

	private OrderBookSide bids;
	private OrderBookSide asks;
	private OrderBook orderBook;
	
	@Before
	public void setUp() {
		bids = OrderBookSideFactory.newBidOrderBookSide();
		asks = OrderBookSideFactory.newAskOrderBookSide();
		orderBook = new OrderBook(bids, asks);
	}
	
	@Test
	public void newOrderBookIsEmpty() {
		assertThat(orderBook.isEmpty(), is(true));
	}
	
	@Test
	public void restOrderOnBidSide() {
		orderBook.restBid(DUMMY_LIMIT_BUY_ORDER);
		assertThat(bids.getAllOrders().size(), is(1));
		assertThat(asks.getAllOrders().size(), is(0));
	}
	
	@Test
	public void restOrderOnAskSide() {
		orderBook.restAsk(DUMMY_LIMIT_SELL_ORDER);
		assertThat(bids.getAllOrders().size(), is(0));
		assertThat(asks.getAllOrders().size(), is(1));
	}
	
}
