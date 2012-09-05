package com.mcorrigal.matchingEngine.matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.mcorrigal.matchingEngine.order.Quantity;

public class QuantityIsEqualTo extends TypeSafeDiagnosingMatcher<Quantity> {
	
	private Quantity expectedQuantity;
	private Matcher<Integer> value;
	
	public QuantityIsEqualTo(Quantity expectedQuantity) {
		this.expectedQuantity = expectedQuantity;
		value = is(equalTo(expectedQuantity.getValue()));
	}
	
	@Override
	protected boolean matchesSafely(Quantity quantity, Description mismatchDescription) {
		boolean matches = true;
		
		if (!value.matches(quantity.getValue())) {
			reportMismatch("value", value, quantity.getValue(), mismatchDescription, matches);
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
		.appendValue(expectedQuantity.getValue())
		.appendText("}");
	}

}
