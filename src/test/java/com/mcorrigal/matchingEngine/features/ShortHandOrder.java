package com.mcorrigal.matchingEngine.features;

import com.mcorrigal.matchingEngine.order.Price;
import com.mcorrigal.matchingEngine.order.Quantity;
import com.mcorrigal.matchingEngine.order.interfaces.Order;

public abstract class ShortHandOrder {

	private static final int QUANTITY_FIELD_INDEX = 0;
	private static final int PRICE_FIELD_INDEX = 1;
	
	private String shortHandOrderDescription;
	protected Price price;
	protected Quantity quantity;
	
	public static BuyShortHandOrder createBuy(String shortHandOrderDescription) {
		return new BuyShortHandOrder(shortHandOrderDescription);
	}
	
	public static SellShortHandOrder createSell(String shortHandOrderDescription) {
		return new SellShortHandOrder(shortHandOrderDescription);
	}
	
	protected ShortHandOrder(String shortHandOrderDescription) {
		this.shortHandOrderDescription = shortHandOrderDescription;
		extractAndSetPrice();
		extractAndSetQuantity();
	}
	
	private void extractAndSetPrice() {
		price = Price.create(extractFieldFromOrderDescription(PRICE_FIELD_INDEX));	
	}
	
	private void extractAndSetQuantity() {
		quantity = Quantity.create(extractFieldFromOrderDescription(QUANTITY_FIELD_INDEX));	
	}
	
	private String extractFieldFromOrderDescription(int fieldIndex) {
		return shortHandOrderDescription.split("@")[fieldIndex].trim();
	}

	public Price getPrice() {
		return price;
	}

	public Quantity getQuantity() {
		return quantity;
	}
	
	public abstract Order toOrder();
	
	
	
}
