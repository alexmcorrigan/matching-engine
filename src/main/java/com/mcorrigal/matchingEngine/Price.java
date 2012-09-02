package com.mcorrigal.matchingEngine;

import java.math.BigDecimal;

public class Price {

	private BigDecimal value;
	
	public static Price create(String value) {
		return new Price(new BigDecimal(value));
	}
	
	public Price(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Boolean isGreaterThan(Price thatPrice) {
		return this.value.compareTo(thatPrice.value) == 1 ? true : false;
	}
	
	public Boolean isLessThan(Price thatPrice) {
		return this.value.compareTo(thatPrice.value) == -1 ? true : false;
	}
	
}
