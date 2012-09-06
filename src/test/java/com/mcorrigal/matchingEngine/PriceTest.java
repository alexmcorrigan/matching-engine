package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PriceTest {
	
	private Price highPrice = Price.create("20");
	private Price lowPrice = Price.create("10");

	@Test
	public void newPriceFromStringRepresentation() {
		Price priceFromString = Price.create("100.89");
		Price priceFromDecimal = new Price(new BigDecimal("100.89"));
		assertThat(priceFromString.equals(priceFromDecimal), is(true));
	}
	
	@Test
	public void isGreaterThan() {
		assertThat(highPrice.isGreaterThan(lowPrice), is(true));
		assertThat(lowPrice.isGreaterThan(highPrice), is(false));
	}
	
	@Test
	public void isLessThan() {
		assertThat(lowPrice.isLessThan(highPrice), is(true));
		assertThat(highPrice.isLessThan(lowPrice), is(false));
	}
	
}
