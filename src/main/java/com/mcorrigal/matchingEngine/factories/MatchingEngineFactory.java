package com.mcorrigal.matchingEngine.factories;

import com.mcorrigal.matchingEngine.MatchingEngine;

public class MatchingEngineFactory {

	public static MatchingEngine newInstance() {
		return new MatchingEngine(OrderBookFactory.newInstance());
	}
	
}
