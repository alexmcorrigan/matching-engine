package com.mcorrigal.matchingEngine.matchers;

import static com.mcorrigal.matchingEngine.matchers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.mcorrigal.matchingEngine.Order;
import com.mcorrigal.matchingEngine.Order.OrderSide;
import com.mcorrigal.matchingEngine.Order.OrderType;
import com.mcorrigal.matchingEngine.OrderId;
import com.mcorrigal.matchingEngine.Price;

public class OrderIsEqualTo extends TypeSafeDiagnosingMatcher<Order> {
	
	private Order expectedOrder;
	private Matcher<OrderId> id;
	private Matcher<OrderSide> side;
	private Matcher<OrderType> type;
	private Matcher<Price> price;
	
	public OrderIsEqualTo(Order expectedOrder) {
		this.expectedOrder = expectedOrder;
		id = is(equalTo(expectedOrder.getId()));
		side = is(equalTo(expectedOrder.getSide()));
		type = is(equalTo(expectedOrder.getType()));
		price = is(equalTo(expectedOrder.getPrice()));
	}
	
	@Override
	protected boolean matchesSafely(Order order, Description mismatchDescription) {
		boolean matches = true;
		
		if (!id.matches(order.getId())) {
			reportMismatch("id", id, order.getId(), mismatchDescription, matches);
			matches = false;
		}
		
		if (!side.matches(order.getSide())) {
			reportMismatch("side", side, order.getSide(), mismatchDescription, matches);
			matches = false;
		}
		
		if (!type.matches(order.getType())) {
			reportMismatch("type", type, order.getType(), mismatchDescription, matches);
			matches = false;
		}
		
		if (!price.matches(order.getPrice())) {
			reportMismatch("price", price, order.getPrice(), mismatchDescription, matches);
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
		description.appendText("{id is ")
		.appendValue(expectedOrder.getId())
		.appendText(", side is ")
		.appendValue(expectedOrder.getSide())
		.appendText(", type is ")
		.appendValue(expectedOrder.getType())
		.appendText(", price is ")
		.appendValue(expectedOrder.getPrice())
		.appendText("}");
	}

}
