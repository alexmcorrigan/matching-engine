package com.mcorrigal.matchingEngine.orderBook;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;

public class SimpleOrderBook implements OrderBook {

	private OrderList bids;
	private OrderList asks;
	
	public SimpleOrderBook(OrderList bids, OrderList asks) {
		this.bids = bids;
		this.asks = asks;
	}

	@Override
	public void newBuyOrder(BuyOrder order) {
		bids.add(order);
	}

	@Override
	public void newSellOrder(SellOrder order) {
		asks.add(order);
	}

    @Override
    public boolean removeBuyOrder(BuyOrder order) {
        return bids.remove(order);
    }

    @Override
    public boolean removeSellOrder(SellOrder order) {
        return asks.remove(order);
    }

    @Override
	public OrderBookSnapshot snapshot() {
		return new OrderBookSnapshot(bids, asks);
	}

    @Override
    public boolean isEmpty() {
        return bids.isEmpty() && asks.isEmpty();
    }

    @Override
    public Order findMatchForBuyOrder(Quantity quantity, Price price) {
        return findMatchForOrder(asks, quantity, price);
    }

    @Override
    public Order findMatchForSellOrder(Quantity quantity, Price price) {
        return findMatchForOrder(bids, quantity, price);
    }

    private Order findMatchForOrder(OrderList orderBookSide, Quantity quantity, Price price) {
        for (Order potentialMatch : orderBookSide.getAll()) {
            if (potentialMatch.isSameShape(quantity, price)) return potentialMatch;
        }
        return null;
    }

}
