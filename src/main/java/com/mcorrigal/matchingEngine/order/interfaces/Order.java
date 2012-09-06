package com.mcorrigal.matchingEngine.order.interfaces;

import com.mcorrigal.matchingEngine.order.ShortHandOrder;
import com.mcorrigal.matchingEngine.order.orderProperties.*;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static com.mcorrigal.matchingEngine.matchers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public abstract class Order extends TypeSafeDiagnosingMatcher<Order> {

    private OrderId id;
	private OrderType type;
	private OrderSide side;
	protected Price price;
	protected Quantity quantity;

    private Matcher<OrderId> idMatcher;
    private Matcher<OrderSide> sideMatcher;
    private Matcher<OrderType> typeMatcher;
    private Matcher<Price> priceMatcher;
    private Matcher<Quantity> quantityMatcher;
	
	public Order(OrderId id, OrderType type, OrderSide side, Price price, Quantity quantity) {
		this.id = id;
		this.type = type;
		this.side = side;
		this.price = price;
		this.quantity = quantity;
        idMatcher = is(equalTo(id));
        sideMatcher = is(side);
        typeMatcher = is(type);
        priceMatcher = is(equalTo(price));
        quantityMatcher = is(equalTo(quantity));
	}

    public boolean isSameShape(Quantity quantity, Price price) {
        return this.quantity.equals(quantity) && this.price.equals(price);
    }

    public boolean isMoreExpensive(Order order) {
        return this.price.isGreaterThan(order.price);
    }

    public boolean isChepaer(Order order) {
        return this.price.isLessThan(order.price);
    }

	@Override
	public String toString() {
		StringBuilder orderString = new StringBuilder();
		orderString.append("{");
		orderString.append(id.getValue());
		orderString.append(" ");
		orderString.append(type.toString());
		orderString.append(" ");
		orderString.append(side.toString());
		orderString.append(" ");
		orderString.append(price.getValue());
		orderString.append(" ");
		orderString.append(quantity.getValue());
		orderString.append("}");
		return orderString.toString();
	}
	
	public abstract void work(OrderBook orderBook);
    public abstract Order findMatch(OrderBook orderBook);
    public abstract void remove(OrderBook orderBook);
    public abstract ShortHandOrder toShortHandOrder();

    public boolean hasId(OrderId id) {
        return this.id.equals(id);
    }

    public String toShortHandNotation() {
        return String.format("%s@%s", quantity.toString(), price.toString());
    }

    @Override
    protected boolean matchesSafely(Order order, Description mismatchDescription) {
        boolean matches = true;

        if (!idMatcher.matches(order.id)) {
            reportMismatch("id", idMatcher, order.id, mismatchDescription, matches);
            matches = false;
        }

        if (!sideMatcher.matches(order.side)) {
            reportMismatch("side", sideMatcher, order.side, mismatchDescription, matches);
            matches = false;
        }

        if (!typeMatcher.matches(order.type)) {
            reportMismatch("type", typeMatcher, order.type, mismatchDescription, matches);
            matches = false;
        }

        if (!priceMatcher.matches(order.price)) {
            reportMismatch("price", priceMatcher, order.price, mismatchDescription, matches);
            matches = false;
        }

        if (!quantityMatcher.matches(order.quantity)) {
            reportMismatch("quantity", quantityMatcher, order.quantity, mismatchDescription, matches);
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
                .appendValue(id.getValue())
                .appendText(", side is ")
                .appendValue(side.toString())
                .appendText(", type is ")
                .appendValue(type.toString())
                .appendText(", price is ")
                .appendValue(price.toString())
                .appendText(", quantity is ")
                .appendValue(quantity.toString())
                .appendText("}");
    }
}
