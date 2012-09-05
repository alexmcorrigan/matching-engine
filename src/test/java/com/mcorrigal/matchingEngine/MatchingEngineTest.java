package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_BUY_ORDER;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_SELL_ORDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.mcorrigal.matchingEngine.mocks.MockOrderBook;

public class MatchingEngineTest {

	private MockOrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	@Before
	public void setUp() {
		orderBook = new MockOrderBook();
		matchingEngine = new MatchingEngine(orderBook);
	}
	
	@Test
	public void receiveNewBuyOrder() {
		matchingEngine.newOrderRequest(DUMMY_LIMIT_BUY_ORDER);
		assertThat(orderBook.getBidCount(), is(1));
		assertThat(orderBook.getAskCount(), is(0));
	}
	
	@Test
	public void receiveNewSellOrder() {
		matchingEngine.newOrderRequest(DUMMY_LIMIT_SELL_ORDER);
		assertThat(orderBook.getBidCount(), is(0));
		assertThat(orderBook.getAskCount(), is(1));
	}
	
}
