package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_BUY_ORDER;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_QUANTITY;
import static com.mcorrigal.matchingEngine.matchers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.OrderId;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.list.PrioritisedOrderList;
import com.mcorrigal.matchingEngine.orderBook.bid.BidOrderComparator;

public class PrioritisedOrderListTest {

	private PrioritisedOrderList orders;

	@Before
	public void setUp() {
		orders = new PrioritisedOrderList(new BidOrderComparator());
	}
	
	@Test
	public void newPrioritisedOrderListIsEmpty() {
		assertThat(orders.isEmpty(), is(true));
	}
	
	@Test
	public void onNewOrderIsNotEmpty() {
		orders.add(DUMMY_LIMIT_BUY_ORDER);
		assertThat(orders.isEmpty(), is(false));
		assertThat(orders.getAll().size(), is(1));
	}
	
	@Test
	public void ordersInListArePrioritisedAfterEachNewOrder() {
		List<Order> restedOrders;
		orders.add(OrderFactory.newLimitBuy("100", "20", DUMMY_QUANTITY));
		orders.add(OrderFactory.newLimitBuy("200", "30", DUMMY_QUANTITY));
		restedOrders = orders.getAll();
		assertThat(restedOrders.get(0).getId(), is(equalTo(OrderId.create("200"))));
		assertThat(restedOrders.get(1).getId(), is(equalTo(OrderId.create("100"))));
		orders.add(OrderFactory.newLimitBuy("300", "40", DUMMY_QUANTITY));
		restedOrders = orders.getAll();
		assertThat(restedOrders.get(0).getId(), is(equalTo(OrderId.create("300"))));
		assertThat(restedOrders.get(1).getId(), is(equalTo(OrderId.create("200"))));
		assertThat(restedOrders.get(2).getId(), is(equalTo(OrderId.create("100"))));
	}
	
}
