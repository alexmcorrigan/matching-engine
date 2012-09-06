package com.mcorrigal.matchingEngine;

import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;
import com.mcorrigal.matchingEngine.orderBook.interfaces.OrderBook;
import org.junit.Before;
import org.junit.Test;

import static com.mcorrigal.matchingEngine.TestConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class OrderTest {

	private OrderBook orderBook;

	@Before
	public void setup() {
		orderBook = mock(OrderBook.class);
	}
	
	@Test
	public void testWorkBuyOrder() {
		DUMMY_LIMIT_BUY_ORDER.work(orderBook);
		verify(orderBook, times(1)).newBuyOrder(DUMMY_LIMIT_BUY_ORDER);
	}
	
	@Test
	public void testWorkSellOrder() {
		DUMMY_LIMIT_SELL_ORDER.work(orderBook);
		verify(orderBook, times(1)).newSellOrder(DUMMY_LIMIT_SELL_ORDER);
	}

    @Test
    public void testFindMatchForBuyOrder() throws Exception {
        DUMMY_LIMIT_BUY_ORDER.findMatch(orderBook);
        verify(orderBook, times(1)).findMatchForBuyOrder(Quantity.create(DUMMY_QUANTITY), Price.create(DUMMY_PRICE));
    }

    @Test
    public void testFindMatchForSellOrder() throws Exception {
        DUMMY_LIMIT_SELL_ORDER.findMatch(orderBook);
        verify(orderBook, times(1)).findMatchForSellOrder(Quantity.create(DUMMY_QUANTITY), Price.create(DUMMY_PRICE));
    }

    @Test
    public void testRemoveBuyFromOrderBook() throws Exception {
        DUMMY_LIMIT_BUY_ORDER.remove(orderBook);
        verify(orderBook, times(1)).removeBuyOrder(DUMMY_LIMIT_BUY_ORDER);
    }

    @Test
    public void testRemoveSellFromOrderBook() throws Exception {
        DUMMY_LIMIT_SELL_ORDER.remove(orderBook);
        verify(orderBook, times(1)).removeSellOrder(DUMMY_LIMIT_SELL_ORDER);
    }

    @Test
    public void testIsSameShape() throws Exception {
        assertThat(DUMMY_LIMIT_BUY_ORDER.isSameShape(Quantity.create(DUMMY_QUANTITY), Price.create(DUMMY_PRICE)), is(true));
    }
}
