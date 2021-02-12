//Vidhi Jain - vj2241
//The Player class creates a player with an initial bankroll of 1000 and their given initial bet, as well
//as an array list of cards which is the player's hand. This class allows you to add cards, remove cards, 
//adjust the bankroll, store the bet and access the player's hand.

import java.util.ArrayList;

public class Player 
{	
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player()
    {		
        bankroll = 1000;
        bet = 0;
        hand = new ArrayList<Card>();
	    // create a player here
	}

	public void addCard(Card c)
    {
        hand.add(c);
	    // add the card c to the player's hand
	}

	public void removeCard(Card c)
    {
        for(int i = 0; i<hand.size(); i++)
        {
            if(hand.get(i).equals(c))
            {
                hand.remove(i);
            }
        }
	    // remove the card c from the player's hand
    }
		
    public ArrayList<Card> getHand() 
    {
        return (hand);
    }
    
    public void bets(double amt)
    {
        bet = amt;
        bankroll = bankroll - amt;
        // player makes a bet
    }

     public void winnings(double odds)
     {
         bankroll = bankroll + (odds*bet);
         //	adjust bankroll if player wins
     }

     public double getBankroll()
     {
         return bankroll;
         // return current balance of bankroll
     }

       // you may wish to use more methods here
}



