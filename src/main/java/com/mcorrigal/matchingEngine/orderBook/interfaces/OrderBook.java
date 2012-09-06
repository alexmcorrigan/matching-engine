package com.mcorrigal.matchingEngine.orderBook.interfaces;

import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;

public interface OrderBook {

	public void newBuyOrder(BuyOrder order);
	public void newSellOrder(SellOrder order);
    public boolean removeBuyOrder(BuyOrder order);
    public boolean removeSellOrder(SellOrder order);
	public OrderBookSnapshot snapshot();
    public boolean isEmpty();
    public Order findMatchForBuyOrder(Quantity quantity, Price price);
    public Order findMatchForSellOrder(Quantity quantity, Price price);

}
