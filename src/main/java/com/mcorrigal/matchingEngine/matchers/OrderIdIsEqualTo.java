package com.mcorrigal.matchingEngine.matchers;

import com.mcorrigal.matchingEngine.order.orderProperties.OrderId;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class OrderIdIsEqualTo extends TypeSafeDiagnosingMatcher<OrderId> {
	
	private OrderId expectedOrderId;
	private Matcher<String> value;
	
	public OrderIdIsEqualTo(OrderId expectedOrderId) {
		this.expectedOrderId = expectedOrderId;
		value = is(equalTo(expectedOrderId.getValue()));
	}
	
	@Override
	protected boolean matchesSafely(OrderId orderId, Description mismatchDescription) {
		boolean matches = true;
		
		if (!value.matches(orderId.getValue())) {
			reportMismatch("value", value, orderId.getValue(), mismatchDescription, matches);
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
		.appendValue(expectedOrderId.getValue())
		.appendText("}");
	}

}
