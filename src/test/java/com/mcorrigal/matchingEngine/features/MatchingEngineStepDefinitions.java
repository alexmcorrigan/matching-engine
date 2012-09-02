package com.mcorrigal.matchingEngine.features;

import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_ORDER_ID;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_PRICE;
import static com.mcorrigal.matchingEngine.features.FeatureStringPatterns.FREE_TEXT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import com.mcorrigal.matchingEngine.MatchingEngine;
import com.mcorrigal.matchingEngine.Order;
import com.mcorrigal.matchingEngine.OrderBook;
import com.mcorrigal.matchingEngine.OrderBookSide;
import com.mcorrigal.matchingEngine.OrderBookSide.OrderBookSideType;
import com.mcorrigal.matchingEngine.OrderBookSideFactory;
import com.mcorrigal.matchingEngine.OrderFactory;
import com.mcorrigal.matchingEngine.features.SpecificationModels.SpecifiedOrder;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class MatchingEngineStepDefinitions {

	private OrderBookSide bids;
	private OrderBookSide asks;
	private OrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	
	@Given("^a matching engine with an empty order book$")
	public void an_empty_order_book() throws Throwable {
		bids = OrderBookSideFactory.newBidOrderBookSide();
		asks = OrderBookSideFactory.newAskOrderBookSide();
		orderBook = new OrderBook(bids, asks);
	    matchingEngine = new MatchingEngine(orderBook);
	}

	@When("^the matching engine receives a limit (" + FREE_TEXT + ") order$")
	public void the_matching_engine_receives_a_limit_buy_order(String orderSide) throws Throwable {
		Order order = OrderFactory.newLimit(DUMMY_ORDER_ID, orderSide, DUMMY_PRICE);
		matchingEngine.newOrder(order);
	}
	
	@When("^the matching engine receives the following limit orders:$")
	public void the_matching_engine_receives_the_following_limit_buy_orders(List<SpecifiedOrder> specifiedOrders) {
		for (SpecifiedOrder specifiedOrder : specifiedOrders) {
			matchingEngine.newOrder(specifiedOrder.createOrder());
		}
	}

	@Then("^the matching engine rests the order on the (" + FREE_TEXT + ") side of the order book$")
	public void the_matching_engine_rests_the_order_on_the_bid_side_of_the_order_book(String orderBookSide) throws Throwable {
	    if (orderBookSide.equalsIgnoreCase("bid")) {
	    	assertOrderBookSideSizes(1, 0);
	    } else if (orderBookSide.equalsIgnoreCase("ask")) { 
	    	assertOrderBookSideSizes(0, 1);
	    } else {
	    	throw new Exception("No order book side called: " + orderBookSide);
	    }
	}
	
	@Then("^the (" + FREE_TEXT + ") order book side has rested the orders in the following order:$")
	public void the_bid_order_book_side_has_rested_the_orders_in_the_following_order(String orderBookSide, List<IdList> specifiedOrderIds) throws Exception {
		List<Order> ordersInOrderBookSide = orderBookSideFor(orderBookSide).getAllOrders();
		assertThat(specifiedOrderIds.size(), is(ordersInOrderBookSide.size()));
		for (int i = 0; i < specifiedOrderIds.size(); i++) {
			String expectedOrderId = (String) specifiedOrderIds.get(i).getId();
			String idOfOrderInOrderBook = ordersInOrderBookSide.get(i).getId().getValue();
			assertThat(idOfOrderInOrderBook, is(equalTo(expectedOrderId)));
		}
	}
	
	private OrderBookSide orderBookSideFor(String orderBookSide) throws Exception {
		if (orderBookSide.equalsIgnoreCase(OrderBookSideType.BID.toString())) {
			return bids;
		} else if (orderBookSide.equalsIgnoreCase(OrderBookSideType.ASK.toString())) {
			return asks;
		} else {
			throw new Exception("Order book side for " + orderBookSide);
		}
	}

	private void assertOrderBookSideSizes(int expectedBids, int expectedAsks) {
		assertThat(bids.getAllOrders().size(), is(expectedBids));
		assertThat(asks.getAllOrders().size(), is(expectedAsks));
	}
	
	private class IdList {
		private String id;
		public String getId() {
			return id;
		}
	}
	
}
