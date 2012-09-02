Feature: Matching Engine Rests Orders with the Same Price and Side in Time Priority
    In order to achieve "best execution" for each new order received, as an exchange operator, I want the first order recieved to have time priority over any newer order received at the same price and side. 

	Scenario: Matching engine receives 2 limit buy orders at same price
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 100 | BUY  | 95.25 |
			| 200 | BUY  | 95.25 |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 200 |
			
	Scenario: Matching engine receives 2 limit sell orders at same price
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side  | price |
			| 100 | SELL  | 95.25 |
			| 200 | SELL  | 95.25 |
			
		Then the ask order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 200 |