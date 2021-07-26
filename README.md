Vidhi Jain - vj2241

Explanation of how my poker game works:

The game begins by creating a Player and a Deck of cards. 

If the player's cards are hardcoded the game then deals the given 5 cards 
to the player which are stored in the Player class as the player's hand.

Once the play method is called the game begins and an intro message as well
as the player's initial bankroll is outputted. The cards are shuffled, the player
is asked how much they would like to bet (the validity of this bet is checked), and 
their bet is stored in the Player class. 

The player is then dealt 5 cards from the top of the deck and they are immediately
added to the player's hand in the Player class. These 5 cards are outputted and the 
player is given the chance to replace any cards. To replace, the player must provide the
number of cards they would like to replace and the index of where each card they would 
like to replace is located. These cards are removed from the hand and new cards are 
added to the hand from the top of the deck. The player's new hand is outputted again.

The play method now calls the checkHand method. 

The checkHand method check's the player's hand in order of hierarchy. It first checks
royal flush, then straight flush, four of a kind, full house, flush, straight, three
of a kind, two pairs, one pair. To check for each of these hands the checkHand method
calls a submethod of the respective hand. These submethods are all boolean methods, once
a method evaluates to true, the name of the hand is returned to the play method in a String. 
If none of the methods evaluate to true, then the String "no pair" is returned.

The play method receives this string with the winning hand and outputs it to the screen.
It also adjusts the bankroll in the player class based on the hand and outputs the updated
bankroll. 

The game displays an ending message and the game ends.
