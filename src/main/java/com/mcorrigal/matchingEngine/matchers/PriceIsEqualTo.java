package com.mcorrigal.matchingEngine.matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.mcorrigal.matchingEngine.order.orderProperties.Price;

public class PriceIsEqualTo extends TypeSafeDiagnosingMatcher<Price> {
	
	private Price expectedPrice;
	private Matcher<BigDecimal> value;
	
	public PriceIsEqualTo(Price expectedPrice) {
		this.expectedPrice = expectedPrice;
		value = is(equalTo(expectedPrice.getValue()));
	}
	
	@Override
	protected boolean matchesSafely(Price price, Description mismatchDescription) {
		boolean matches = true;
		
		if (!value.matches(price.getValue())) {
			reportMismatch("value", value, price.getValue(), mismatchDescription, matches);
			matches = false;
		}
		
		return matches; 
	}
	
	private void reportMismatch(String name, Matcher<?> matcher, Object item, Description mismatchDescription, boolean isFirstMismatch) {
		if (!isFirstMismatch) {
			mismatchDescription.appendText(", ");
		}
		mismatchDescription.appendText(name + " ");
		matcher.describeMismatch(item, mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("{value is ")
		.appendValue(expectedPrice.getValue())
		.appendText("}");
	}

}
