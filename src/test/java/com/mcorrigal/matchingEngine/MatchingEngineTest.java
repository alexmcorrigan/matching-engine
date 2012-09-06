package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.factories.OrderFactory;
import com.mcorrigal.matchingEngine.order.BuyOrder;
import com.mcorrigal.matchingEngine.order.SellOrder;
import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MatchingEngineTest {

	private OrderBook orderBook;
	private MatchingEngine matchingEngine;
	
	@Before
	public void setUp() {
		orderBook = mock(OrderBook.class);
		matchingEngine = new MatchingEngine(orderBook);
	}
	
	@Test
	public void receiveNewBuyOrder() {
		Order order = mock(Order.class);
		matchingEngine.newOrderRequest(order);
		verify(order, times(1)).work(orderBook);
	}
	
	@Test
	public void matchingEngineMatchesBuyOrderToSellOrderAtEqualPriceAndQuantity() {
    	SellOrder sell = OrderFactory.newLimitSell("SELL", "30", "100");
		BuyOrder buy = OrderFactory.newLimitBuy("BUY", "30", "100");
        doReturn(sell).when(orderBook).findMatchForBuyOrder(Quantity.create("100"), Price.create("30"));
		matchingEngine.newOrderRequest(buy);
		verify(orderBook, never()).newBuyOrder(buy);
		verify(orderBook, times(1)).removeSellOrder(sell);
	}

    @Test
    public void matchingEngineMatchesSellOrderToSellOrderAtEqualPriceAndQuantity() {
        BuyOrder buy = OrderFactory.newLimitBuy("BUY", "30", "100");
        SellOrder sell = OrderFactory.newLimitSell("SELL", "30", "100");
        doReturn(buy).when(orderBook).findMatchForSellOrder(Quantity.create("100"), Price.create("30"));
        matchingEngine.newOrderRequest(sell);
        verify(orderBook, never()).newSellOrder(sell);
        verify(orderBook, times(1)).removeBuyOrder(buy);
    }

}
