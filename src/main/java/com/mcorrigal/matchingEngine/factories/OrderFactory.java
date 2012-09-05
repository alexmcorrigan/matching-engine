package com.mcorrigal.matchingEngine.factories;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.Order.OrderSide;
import com.mcorrigal.matchingEngine.order.interfaces.Order.OrderType;
import com.mcorrigal.matchingEngine.order.OrderId;
import com.mcorrigal.matchingEngine.order.Price;
import com.mcorrigal.matchingEngine.order.Quantity;
import com.mcorrigal.matchingEngine.order.SellOrder;

public class OrderFactory {
	
	public static Order newLimit(String id, String side, String price, String quantity) {
		if (OrderSide.valueOf(side.toUpperCase()).equals(OrderSide.BUY)) {
			return newLimitBuy(id, price, quantity);
		} else {
			return newLimitSell(id, price, quantity);
		}
	}
	
	public static BuyOrder newLimitBuy(String id, String price, String quantity) {
		return newLimitBuy(
				OrderId.create(id), 
				Price.create(price), 
				Quantity.create(quantity));
	}

	public static SellOrder newLimitSell(String id, String price, String quantity) {
		return newLimitSell(
				OrderId.create(id), 
				Price.create(price), 
				Quantity.create(quantity));
	}
	
	public static BuyOrder newLimitBuy(OrderId id, Price price, Quantity quantity) {
		return new BuyOrder(
				id, 
				OrderType.LIMIT, 
				price, 
				quantity);
	}

	public static SellOrder newLimitSell(OrderId id, Price price, Quantity quantity) {
		return new SellOrder(
				id, 
				OrderType.LIMIT, 
				price, 
				quantity);
	}
	
}
