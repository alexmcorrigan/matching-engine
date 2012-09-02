package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.dummyLimitBuyForPrice;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BidOrderComparatorTest {

	private OrderComparator bidOrderComparator;
	private Order highPricedOrder = dummyLimitBuyForPrice("50");
	private Order lowPricedOrder = dummyLimitBuyForPrice("40");
	
	@Before
	public void setUp() {
		bidOrderComparator = new BidOrderComparator();
	}
	
	@Test
	public void lowPricedOrderIsLowerPriorityThanHighPricedOrder() {
		assertThat(bidOrderComparator.compare(lowPricedOrder, highPricedOrder), is(equalTo(1)));
	}
	
	@Test
	public void highPricedOrderIsHigherPriorityThanLowPricedOrder() {
		assertThat(bidOrderComparator.compare(highPricedOrder, lowPricedOrder), is(equalTo(-1)));
	}
	
	
	@Test
	public void twoEqualPricedOrdersHaveSamePricePriority() {
		assertThat(bidOrderComparator.compare(highPricedOrder, highPricedOrder), is(equalTo(0)));
	}
	
}
