package com.mcorrigal.matchingEngine;

import org.junit.Test;

import static com.mcorrigal.matchingEngine.Order.OrderSide.BUY;
import static com.mcorrigal.matchingEngine.Order.OrderSide.SELL;
import static com.mcorrigal.matchingEngine.TestConstants.DUMMY_PRICE;
import static com.mcorrigal.matchingEngine.TestConstants.*;

import com.mcorrigal.matchingEngine.Order.OrderSide;
import com.mcorrigal.matchingEngine.Order.OrderType;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.mcorrigal.matchingEngine.matchers.Matchers.*;


public class OrderFactoryTest {
	
	@Test
	public void newLimitBuyOrders() {
		assertThat(DUMMY_LIMIT_BUY_ORDER, is(equalTo(orderFromConstructor(BUY))));
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("buy", BUY);
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("Buy", BUY);
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("BUY", BUY);
	}
	
	@Test
	public void newLimitSellOrders() {
		assertThat(DUMMY_LIMIT_SELL_ORDER, is(equalTo(orderFromConstructor(SELL))));
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("sell", SELL);
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("Sell", SELL);
		assertThatOrderFromFactoryIsEqualToOrderFromConstructor("SELL", SELL);
	}
	
	private void assertThatOrderFromFactoryIsEqualToOrderFromConstructor(String sideString, OrderSide side) {
		assertThat(orderFromOrderFactoryWithSide(sideString), is(equalTo(orderFromConstructor(side))));
	}
	
	private Order orderFromOrderFactoryWithSide(String side) {
		return OrderFactory.newLimit(DUMMY_ORDER_ID, side, DUMMY_PRICE);
	}
	
	private Order orderFromConstructor(OrderSide side) {
		return new Order(
				OrderId.create(DUMMY_ORDER_ID), 
				OrderType.LIMIT, 
				side, 
				Price.create(DUMMY_PRICE));
	}
	
}
