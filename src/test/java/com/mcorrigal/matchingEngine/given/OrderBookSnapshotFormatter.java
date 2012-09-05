package com.mcorrigal.matchingEngine.given;


import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.interfaces.OrderList;
import com.mcorrigal.matchingEngine.orderBook.OrderBookSnapshot;

public class OrderBookSnapshotFormatter {

	public String format(OrderBookSnapshot snapshot) {
		StringBuilder snapshotBuilder = new StringBuilder();
		snapshotBuilder.append("received order book snapshot");
		snapshotBuilder.append(String.format("\n%-8s | %-8s \n", "Bids", "Asks"));
		snapshotBuilder.append("-------------------\n");
		snapshotBuilder.append(formatSnapshotOrderLevels(snapshot));
		return snapshotBuilder.toString();
	}

	private String formatSnapshotOrderLevels(OrderBookSnapshot snapshot) {
		StringBuilder orderLevelBuilder = new StringBuilder();
		int rows = Math.max(snapshot.getBidSideSnapshot().size(), snapshot.getAskSideSnapshot().size());
		for (int orderLevel = 0; orderLevel < rows; orderLevel++) {
			String bidSideOrder = getSnapshotOrderAtLevel(orderLevel, snapshot.getBidSideSnapshot());
			String askSideOrder = getSnapshotOrderAtLevel(orderLevel, snapshot.getAskSideSnapshot());
			orderLevelBuilder.append(String.format("%-8s | %-8s\n", bidSideOrder, askSideOrder));
		}
		return orderLevelBuilder.toString();
	}
	
	private String getSnapshotOrderAtLevel(int orderLevel, OrderList snapShotOrdersForSide) {
		int totalSnapShotOrders = snapShotOrdersForSide.size();
		return totalSnapShotOrders == 0 || totalSnapShotOrders < orderLevel ? "" : buildOrderString(snapShotOrdersForSide.get(orderLevel));
	}
	
	private String buildOrderString(Order order) {
		return  order.getQuantity().getValue() + "@" + order.getPrice().getValue();
	}

}
