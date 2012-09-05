package com.mcorrigal.matchingEngine.orderBook.interfaces;


import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;
import com.mcorrigal.matchingEngine.orderBook.ask.AskOrderBookSide;
import com.mcorrigal.matchingEngine.orderBook.bid.BidOrderBookSide;


public abstract class OrderBookSide {

	public static enum OrderBookSideName {
		
		ASK(AskOrderBookSide.class), 
		BID(BidOrderBookSide.class);
		
		private Class<? extends OrderBookSide> associatedOrderBookSideClass;
		
		private OrderBookSideName(Class<? extends OrderBookSide> associatedOrderBookSideClass) {
			this.associatedOrderBookSideClass = associatedOrderBookSideClass;
		}

		public OrderBookSide createOrderBookSide() throws InstantiationException, IllegalAccessException {
			return associatedOrderBookSideClass.newInstance();
		}
		
	}
	
	private OrderBookSideName side;
	
	protected OrderBookSide(OrderBookSideName side) {
		this.side = side;
	}
	
	public OrderBookSideName getSide() {
		return side;
	}
	
	public abstract OrderList getOrderBookSideSnapshotFrom(OrderBookSnapshot orderBookSnapshot);
	
}
