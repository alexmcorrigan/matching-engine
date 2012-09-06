package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.orderProperties.OrderId;
import com.mcorrigal.matchingEngine.order.orderProperties.OrderSide;
import com.mcorrigal.matchingEngine.order.orderProperties.OrderType;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;


public class SellOrder extends Order {

	public SellOrder(OrderId id, OrderType type, Price price, Quantity quantity) {
		super(id, type, OrderSide.SELL, price, quantity);
	}

	@Override
	public void work(OrderBook orderBook) {
		orderBook.newSellOrder(this);
	}

    @Override
    public Order findMatch(OrderBook orderBook) {
        return orderBook.findMatchForSellOrder(quantity, price);
    }

    @Override
    public void remove(OrderBook orderBook) {
        orderBook.removeSellOrder(this);
    }

}
