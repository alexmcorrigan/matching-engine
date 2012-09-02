package com.mcorrigal.matchingEngine;


public class Order {

	public static enum OrderType {LIMIT}
	public static enum OrderSide {BUY, SELL} 
	
	private OrderId id;
	private OrderType type;
	private OrderSide side;
	private Price price;
	
	public Order(OrderId id, OrderType type, OrderSide side, Price price) {
		this.id = id;
		this.type = type;
		this.side = side;
		this.price = price;
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
		orderString.append("}");
		return orderString.toString();
	}
	
}
