package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.list.SimpleOrderList;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.SimpleOrderBook;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;
import org.junit.Before;
import org.junit.Test;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_BUY_ORDER;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_LIMIT_SELL_ORDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleOrderBookTest {
	private OrderList bids;
	private OrderList asks;
	private OrderBook orderBook;

	@Before
	public void setUp() {
		bids = new SimpleOrderList();
		asks = new SimpleOrderList();
		orderBook = new SimpleOrderBook(bids, asks);
	}
	
	@Test
	public void newSimpleOrderBook() {
		assertThat(orderBook.isEmpty(), is(true));
	}
	
	@Test
	public void addBuyOrder() {
		orderBook.newBuyOrder(DUMMY_LIMIT_BUY_ORDER);
		assertTrue(bids.size() == 1);
		assertTrue(asks.size() == 0);
	}
	
	@Test
	public void addSellOrder() {
		orderBook.newSellOrder(DUMMY_LIMIT_SELL_ORDER);
		assertTrue(bids.size() == 0);
		assertTrue(asks.size() == 1);
	}
	
	@Test
	public void removeBuyOrder() {
		BuyOrder buyA = OrderFactory.newLimitBuy("A", "25", "100");
		BuyOrder buyB = OrderFactory.newLimitBuy("B", "26", "100");
		orderBook.newBuyOrder(buyA);
		orderBook.newBuyOrder(buyB);
		assertThat("buy order removed from orderBook", orderBook.removeBuyOrder(buyA), is(true));
		assertThat("number of bids in order book", bids.size(), is(1));
		assertThat("remaining order in order book", bids.get(0).equals(buyB), is(true));
	}
	
	@Test
	public void removeSellOrder() {
		SellOrder sellA = OrderFactory.newLimitSell("A", "25", "100");
		SellOrder sellB = OrderFactory.newLimitSell("B", "26", "100");
		orderBook.newSellOrder(sellA);
		orderBook.newSellOrder(sellB);
		assertThat("sell order removed from orderBook", orderBook.removeSellOrder(sellA), is(true));
		assertThat("number of asks in order book", asks.size(), is(1));
        assertThat("remaining order in order book", asks.get(0).equals(sellB), is(true));
	}
	
	@Test
	public void snapshotOrderBook() {
		BuyOrder buyA = OrderFactory.newLimitBuy("A", "25", "100");
		SellOrder sellA = OrderFactory.newLimitSell("A", "25", "100");
		orderBook.newBuyOrder(buyA);
		orderBook.newSellOrder(sellA);
		OrderBookSnapshot snapshot = orderBook.snapshot();
        assertTrue(snapshot.getBidSideSnapshot().getAll().contains(buyA));
        assertTrue(snapshot.getAskSideSnapshot().getAll().contains(sellA));
	}

    @Test
    public void testFindMatchForBuyOrder() throws Exception {
        SellOrder sellOrder = OrderFactory.newLimitSell("SELL", "95", "100");
        orderBook.newSellOrder(sellOrder);
        Order match = orderBook.findMatchForBuyOrder(Quantity.create("100"), Price.create("95"));
        assertThat(match.equals(sellOrder), is(true));
    }

    @Test
    public void testFindMatchForSellOrder() throws Exception {
        BuyOrder buyOrder = OrderFactory.newLimitBuy("BUY", "95", "100");
        orderBook.newBuyOrder(buyOrder);
        Order match = orderBook.findMatchForSellOrder(Quantity.create("100"), Price.create("95"));
        assertThat(match.equals(buyOrder), is(true));
    }

}
