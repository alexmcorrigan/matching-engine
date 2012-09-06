package com.mcorrigal.matchingEngine.order.orderProperties;

public class OrderId {

	private String value;

	public static OrderId create(String value) {
		return new OrderId(value);
	}
	
	private OrderId(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == OrderId.class) {
            OrderId i = (OrderId) o;
            return value.equals(i.value);
        } else {
            return false;
        }
    }
}
