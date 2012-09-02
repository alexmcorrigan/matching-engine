package com.mcorrigal.matchingEngine;

import static com.mcorrigal.matchingEngine.matchers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class PriceTest {
	
	private Price highPrice = Price.create("20");
	private Price lowPrice = Price.create("10");

	@Test
	public void newPriceFromStringRepresentation() {
		Price priceFromString = Price.create("100.89");
		Price priceFromDecimal = new Price(new BigDecimal("100.89"));
		assertThat(priceFromString, is(equalTo(priceFromDecimal)));
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
