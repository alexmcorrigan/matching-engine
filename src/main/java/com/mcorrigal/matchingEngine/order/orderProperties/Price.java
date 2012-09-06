package com.mcorrigal.matchingEngine.order.orderProperties;

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
		return this.value.compareTo(thatPrice.value) == 1;
	}
	
	public Boolean isLessThan(Price thatPrice) {
		return this.value.compareTo(thatPrice.value) == -1;
	}

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Price.class) {
            Price p = (Price) o;
            return value.compareTo(p.value) == 0;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
