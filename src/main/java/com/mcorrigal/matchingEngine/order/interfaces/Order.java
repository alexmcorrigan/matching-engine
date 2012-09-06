package com.mcorrigal.matchingEngine.order.interfaces;

import com.mcorrigal.matchingEngine.order.orderProperties.*;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public abstract class Order {

    private OrderId id;
	private OrderType type;
	private OrderSide side;
	protected Price price;
	protected Quantity quantity;

	protected Order(OrderId id, OrderType type, OrderSide side, Price price, Quantity quantity) {
		this.id = id;
		this.type = type;
		this.side = side;
		this.price = price;
		this.quantity = quantity;
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
		orderString.append(id.toString());
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

    public boolean hasId(OrderId id) {
        return this.id.equals(id);
    }

    public boolean isSameSizeAs(Order order) {
        return quantity.equals(order.quantity);
    }

    public boolean isSamePriceAs(Order order) {
        return price.equals(order.price);
    }

    public boolean isSameTypeAs(Order order) {
        return type.equals(order.type);
    }

    public boolean isSameSideAs(Order order) {
        return side.equals(order.side);
    }

    public String toShortHandNotationString() {
        return String.format("%s@%s", quantity.toString(), price.toString());
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if (o instanceof Order) {
            Order order = (Order) o;
            equal = order.hasId(id)
                    && order.isSameTypeAs(order)
                    && order.isSameSideAs(order)
                    && order.isSameSizeAs(order)
                    && order.isSamePriceAs(order);
        }
        return equal;
    }
}
