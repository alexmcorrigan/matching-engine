Feature: Buy orders listed in the order book will be ordered first by price, and then for orders at each price, by time.

	Scenario: Matching engine receives three orders, two at the same price, one at a different price
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 100 | BUY  | 30    |
			| 200 | BUY  | 20    |
			| 300 | BUY  | 30    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 300 |
			| 200 |
			
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 400 | BUY  | 30    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 300 |
			| 400 |
			| 200 |
			
		When the matching engine receives the following limit orders:
			| id  | side | price |
			| 500 | BUY  | 20    |
			
		Then the bid order book side has rested the orders in the following order:
			| id  |
			| 100 |
			| 300 |
			| 400 |
			| 200 |
			| 500 |