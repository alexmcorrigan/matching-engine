Feature: Sell Order Trades with Resting Buy Order At Same Price

	Scenario: Both Sell and Buy Order Filled
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | BUY  | 100      | 30    |
			| 100 | SELL | 100      | 30    |

		Then the order is book is empty

	Scenario: Sell order filled, buy order partially filled

		Given a matching engine with an empty order book

		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | BUY  | 150      | 30    |
			| 100 | SELL | 100      | 30    |

		Then the order book shows:
		    | bid     | ask |
		    | 50 @ 30 |     |

	Scenario: Sell order partially filled, buy order filled

		Given a matching engine with an empty order book

		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | BUY  | 50       | 30    |
			| 100 | SELL | 100      | 30    |

		Then the order book shows:
		    | bid | ask     |
		    |     | 50 @ 30 |