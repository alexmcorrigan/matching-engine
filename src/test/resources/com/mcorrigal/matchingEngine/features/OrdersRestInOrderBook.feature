Feature: Matching Engine Rests Limit Orders on its Order Book
    As a trader, in order to buy or sell an asset at a price I choose, I want the exchange's matching engine to hold on to my order if there are no exisitng matches for it.

	Scenario: Limit Buy order will rest on the Bid side of an empty order book
		Given a matching engine with an empty order book
		When the matching engine receives a limit buy order
		Then the matching engine rests the order on the bid side of the order book

	Scenario: Limit Sell order will rest on the Ask side of an empty order book
		Given a matching engine with an empty order book
		When the matching engine receives a limit sell order
		Then the matching engine rests the order on the ask side of the order book