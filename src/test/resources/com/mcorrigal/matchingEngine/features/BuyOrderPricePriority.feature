Feature: Buy Orders will be Rested in the Order Book in Price Priority
    In order to execute trades fairly, as an exchange operator, I want higher priced buy orders to have price priority over lower priced buy orders regardless of sequence in which received.

	Scenario: Matching engine receives lower priced buy order before higher priced buy order.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 100 | BUY  | 20    |
			| 200 | BUY  | 30    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 200 |
			| 100 |
			
	Scenario: Matching engine receives higher priced buy order before lower priced buy order.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 100 | BUY  | 30    |
			| 200 | BUY  | 20    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 200 |
			
	Scenario: Matching engine receives new order priced between two previous orders.
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 100 | BUY  | 20    |
			| 200 | BUY  | 30    |
			| 300 | BUY  | 25    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 200 |
			| 300 |
			| 100 |