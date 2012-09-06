package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.order.list.SimpleOrderList;
import org.junit.Before;
import org.junit.Test;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_BUY_ORDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleOrderListTest {

	private SimpleOrderList orders;

	@Before
	public void setUp() {
		orders = new SimpleOrderList();
	}
	
	@Test
	public void newSimpleOrderListIsEmpty() {
		assertThat(orders.isEmpty(), is(true));
	}
	
	@Test
	public void onNewOrderIsNotEmpty() {
		orders.add(DUMMY_LIMIT_BUY_ORDER);
		assertThat(orders.isEmpty(), is(false));
		assertThat(orders.size(), is(1));
	}

}
