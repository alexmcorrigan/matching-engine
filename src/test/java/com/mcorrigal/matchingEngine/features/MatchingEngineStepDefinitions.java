package com.mcorrigal.matchingEngine.features;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.features.SpecificationModels.SpecifiedOrder;
import com.mcorrigal.matchingEngine.features.SpecificationModels.SpecifiedOrderBookLevel;
import com.mcorrigal.matchingEngine.given.Environment;
import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.orderProperties.OrderId;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBookSide;
import com.mcorrigal.matchingEngine.utils.OrderBookUtils;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import java.util.List;

import static com.mcorrigal.matchingEngine.TestConstants.*;
import static com.mcorrigal.matchingEngine.features.FeatureStringPatterns.FREE_TEXT;
import static com.mcorrigal.matchingEngine.utils.OrderBookUtils.orderBookSideFrom;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class MatchingEngineStepDefinitions {
	
	private Environment environment;
	
	@Given("^a matching engine with an empty order book$")
	public void anEmptyOrderBook() {
		environment = new Environment();
	}

	@Given("^a matching engine with the following order book:$")
	public void aLoadedOrderBook(List<SpecifiedOrderBookLevel> orderBookLevels) throws Throwable {
		environment = new Environment();
		
		for (SpecifiedOrderBookLevel bookLevel : orderBookLevels) {
			BuyOrder buyOrder = bookLevel.getBidSideAsShortHandDescription().manufactureNewOrder();
			SellOrder sellOrder = bookLevel.getAskSideAsShortHandDescription().manufactureNewOrder();
			if (buyOrder != null) sendOrder(buyOrder);
			if (sellOrder != null) sendOrder(sellOrder);
		}
	}

    private void sendOrder(Order order) {
		environment.getRealMatchingEngine().hasReceived(order);
	}
	
	@When("^the matching engine receives a limit (" + FREE_TEXT + ") order$")
	public void receiveLimitOrder(String orderSide) throws Throwable {
		Order order = OrderFactory.newLimit(DUMMY_ORDER_ID, orderSide, DUMMY_PRICE, DUMMY_QUANTITY);
		environment.getRealMatchingEngine().hasReceived(order);
	}

	@When("^the matching engine receives the following limit orders:$")
	public void receiveLimitOrders(List<SpecifiedOrder> specifiedOrders) {
		for (SpecifiedOrder specifiedOrder : specifiedOrders) {
			environment.getRealMatchingEngine().hasReceived(specifiedOrder.createOrder());
		}
	}
	
	@Then("^the matching engine rests the order on the (" + FREE_TEXT + ") side of the order book$")
	public void orderListOnOrderBookSide(String orderBookSide) throws Throwable {
	    if (orderBookSide.equalsIgnoreCase("bid")) {
	    	assertOrderBookSideSizes(1, 0);
	    } else if (orderBookSide.equalsIgnoreCase("ask")) { 
	    	assertOrderBookSideSizes(0, 1);
	    } else {
	    	throw new Exception("No order book side called: " + orderBookSide);
	    }
	}
	
	@Then("^the (" + FREE_TEXT + ") order book side has rested the orders in the following order:$")
	public void ordersListedOnOrderBookSide(String orderBookSide, List<IdList> specifiedOrderIds) throws Exception {
		OrderBookSide side = orderBookSideFrom(orderBookSide);
		OrderList ordersInOrderBookSide = publishedOrderBookSideFor(side);
		assertThat(specifiedOrderIds.size(), is(ordersInOrderBookSide.size()));
		
		for (int i = 0; i < specifiedOrderIds.size(); i++) {
			OrderId expectedOrderId = OrderId.create(specifiedOrderIds.get(i).getId());
			assertThat(ordersInOrderBookSide.get(i).hasId(expectedOrderId), is(true));
		}
	}

    @Then("^the order is book is empty$")
    public void the_order_is_book_is_empty() {
        assertThat(environment.getRealMatchingEngine().orderBookIsEmpty(), is(true));
    }

    @Then("^the order book shows:$")
    public void the_order_book_shows(List<SpecifiedOrderBookLevel> expectedOrderBookSnapshot) {
        OrderBookSnapshot orderBookSnapshot = environment.snapshotOrderBookNow();
        for (SpecifiedOrderBookLevel expectedBookLevel : expectedOrderBookSnapshot) {
            assertTrue(OrderBookUtils.orderListContainsOrder(orderBookSnapshot.getBidSideSnapshot(), expectedBookLevel.getBidSideAsShortHandDescription()));
            assertTrue(OrderBookUtils.orderListContainsOrder(orderBookSnapshot.getAskSideSnapshot(), expectedBookLevel.getAskSideAsShortHandDescription()));
        }
    }

	private OrderList publishedOrderBookSideFor(OrderBookSide orderBookSide) throws Exception {
		return orderBookSide.getOrderBookSideSnapshotFrom(lastPublishedOrderBookSnapshot());
	}

	private void assertOrderBookSideSizes(int expectedBids, int expectedAsks) {
		OrderBookSnapshot lastOrderBookSnapshot = lastPublishedOrderBookSnapshot();
		assertThat(lastOrderBookSnapshot.getBidSideSnapshot().size(), is(expectedBids));
		assertThat(lastOrderBookSnapshot.getAskSideSnapshot().size(), is(expectedAsks));
	}
	
	private OrderBookSnapshot lastPublishedOrderBookSnapshot() {
		return environment.getLastPublishedOrderBookSnapshot();
	}
	
	private class IdList {
		private String id;
		public String getId() {
			return id;
		}
	}
	
}
