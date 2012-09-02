Feature: Sell Orders at the same Price will be Listed in the Order Book in Time Priority
    In order to achieve "best execution" for each new order received, as an exchange operator, I want the first order recieved to have time priority over any newer order received at the same price and side. 

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