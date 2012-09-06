package com.mcorrigal.matchingEngine.order.orderProperties;


public class Quantity {

	private int value;
	
	public static Quantity create(String value) {
		return new Quantity(Integer.valueOf(value));
	}
	
	public Quantity(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Quantity.class) {
            Quantity q = (Quantity) o;
            return value == q.value;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
