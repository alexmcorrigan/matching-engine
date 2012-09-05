package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.dummyLimitSellForPrice;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.orderBook.ask.AskOrderComparator;

public class AskOrderComparatorTest {

	private AskOrderComparator askOrderComparator;
	private SellOrder highPricedOrder = dummyLimitSellForPrice("50");
	private SellOrder lowPricedOrder = dummyLimitSellForPrice("40");
	
	@Before
	public void setUp() {
		askOrderComparator = new AskOrderComparator();
	}
	
	@Test
	public void lowPricedOrderIsHigherPriorityThanHighPricedOrder() {
		assertThat(askOrderComparator.compare(lowPricedOrder, highPricedOrder), is(equalTo(-1)));
	}
	
	@Test
	public void highPricedOrderIsLowerPriorityThanLowPricedOrder() {
		assertThat(askOrderComparator.compare(highPricedOrder, lowPricedOrder), is(equalTo(1)));
	}
	
	
	@Test
	public void twoEqualPricedOrdersHaveSamePricePriority() {
		assertThat(askOrderComparator.compare(highPricedOrder, highPricedOrder), is(equalTo(0)));
	}
	
}
