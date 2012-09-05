package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_BUY_ORDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.mcorrigal.matchingEngine.matchers.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderBookSideTest {

	private OrderBookSide orderBookSide;

	@Before
	public void setUp() {
		orderBookSide = OrderBookSideFactory.newBidOrderBookSide();
	}
	
	@Test
	public void newOrderBookSideIsEmpty() {
		assertThat(orderBookSide.isEmpty(), is(true));
	}
	
	@Test
	public void restOrder() {
		orderBookSide.rest(DUMMY_LIMIT_BUY_ORDER);
		assertThat(orderBookSide.isEmpty(), is(false));
		assertThat(orderBookSide.getAllOrders().size(), is(1));
	}
	
	@Test
	public void ordersArePrioritisedAfterEachResting() {
		List<Order> restedOrders;
		orderBookSide.rest(OrderFactory.newLimitBuy("100", "20"));
		orderBookSide.rest(OrderFactory.newLimitBuy("200", "30"));
		restedOrders = orderBookSide.getAllOrders();
		assertThat(restedOrders.get(0).getId(), is(equalTo(OrderId.create("200"))));
		assertThat(restedOrders.get(1).getId(), is(equalTo(OrderId.create("100"))));
		orderBookSide.rest(OrderFactory.newLimitBuy("300", "40"));
		restedOrders = orderBookSide.getAllOrders();
		assertThat(restedOrders.get(0).getId(), is(equalTo(OrderId.create("300"))));
		assertThat(restedOrders.get(1).getId(), is(equalTo(OrderId.create("200"))));
		assertThat(restedOrders.get(2).getId(), is(equalTo(OrderId.create("100"))));
	}
	
}
