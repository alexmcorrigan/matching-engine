package com.mcorrigal.matchingEngine.order.interfaces;

import com.mcorrigal.matchingEngine.order.OrderId;
import com.mcorrigal.matchingEngine.order.Price;
import com.mcorrigal.matchingEngine.order.Quantity;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public abstract class Order {

	public static enum OrderType {LIMIT}
	public static enum OrderSide {BUY, SELL}
	
	private OrderId id;
	private OrderType type;
	private OrderSide side;
	private Price price;
	private Quantity quantity;
	
	public Order(OrderId id, OrderType type, OrderSide side, Price price, Quantity quantity) {
		this.id = id;
		this.type = type;
		this.side = side;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderType getType() {
		return type;
	}

	public OrderSide getSide() {
		return side;
	}

	public Price getPrice() {
		return price;
	}

	public OrderId getId() {
		return id;
	}
	
	public Quantity getQuantity() {
		return quantity;
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
	
}
