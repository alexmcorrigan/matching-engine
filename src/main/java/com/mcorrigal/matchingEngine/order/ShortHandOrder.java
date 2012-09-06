package com.mcorrigal.matchingEngine.order;

import com.mcorrigal.matchingEngine.order.interfaces.Order;
import com.mcorrigal.matchingEngine.order.orderProperties.OrderId;
import com.mcorrigal.matchingEngine.order.orderProperties.Price;
import com.mcorrigal.matchingEngine.order.orderProperties.Quantity;

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

    @Override
    public boolean equals(Object o) {
        try {
            ShortHandOrder otherShortHandOrder = (ShortHandOrder) o;
            return price == otherShortHandOrder.price && quantity == otherShortHandOrder.quantity;
        } catch (Exception e) {
            return false;
        }
    }

    public abstract Order manufactureNewOrder();

    protected OrderId createNewOrderIdFromTime() {
        return OrderId.create(String.valueOf(System.currentTimeMillis()));
    }

}
