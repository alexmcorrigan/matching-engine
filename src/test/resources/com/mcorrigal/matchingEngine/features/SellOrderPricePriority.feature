Feature: Sell Orders will be Rested in the Order Book in Price Priority
    In order to execute trades fairly, as an exchange operator, I want lower priced sell orders to have price priority over higher priced sell orders regardless of sequence in which received.

	Scenario: Matching engine receives higher priced sell order before lower priced buy order.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side  | price |
			| 100 | SELL  | 30    |
			| 200 | SELL  | 20    |
			
		Then the ask order book side has rested the orders in the following order:
			| id  |
			| 200 |
			| 100 |
			
	Scenario: Matching engine receives lower priced sell order before higher priced sell order.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side  | price |
			| 100 | SELL  | 20    |
			| 200 | SELL  | 30    |
			
		Then the ask order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 200 |
			
	Scenario: Matching engine receives new order priced between two previous orders.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side  | price |
			| 100 | SELL  | 30    |
			| 200 | SELL  | 20    |
			| 300 | SELL  | 25    |
			
		Then the ask order book side has rested the orders in the following order:
			| id  |
			| 200 |
			| 300 |
			| 100 |