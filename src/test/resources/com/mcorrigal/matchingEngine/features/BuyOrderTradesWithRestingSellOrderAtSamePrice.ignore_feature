Feature: Buy Order Trades with Resting Sell Order At Same Price

	Scenario: Both Buy and Sell Order Filled
		
		Given a matching engine with an empty order book
		
		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | SELL | 100      | 30    |
			| 100 | BUY  | 100      | 30    |

		Then the order is book is empty

	Scenario: Buy order filled, sell order partially filled

		Given a matching engine with an empty order book

		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | SELL | 150      | 30    |
			| 100 | BUY  | 100      | 30    |

		Then the order book shows:
		    | bid | ask     |
		    |     | 50 @ 30 |

	Scenario: Buy order partially filled, sell order filled

		Given a matching engine with an empty order book

		When the matching engine receives the following limit orders:
			| id  | side | quantity | price |
			| 100 | SELL | 50       | 30    |
			| 100 | BUY  | 100      | 30    |

		Then the order book shows:
		    | bid     | ask |
		    | 50 @ 30 |     |