//Vidhi Jain - vj2241
//The Deck class creates a Deck of cards with all 4 suits and 13 ranks and keep track of which card is on top. 
//This class also allows you to shuffle the deck of cards.

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

// add your own banner here

public class Deck
{
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck()
    {
        cards = new Card[52];
        
        for (int i = 0; i < 52; i++)
        {
            if(i<13)
            {
                cards[i] = new Card(1, i+1);
            }
            else if (i<26)
            {
                cards[i] = new Card(2, i-12);
            }
            else if (i<39)
            {
                cards[i] = new Card(3, i-25);
            }
            else
            {
                cards[i] = new Card(4, i-38);
            }
            
        }
        
        top = 0;
		// make a 52 card deck here
	}
	
	public void shuffle()
    {
        List<Card> nums = Arrays.asList(cards);
        Collections.shuffle(nums);
        nums.toArray(cards);
        
		// shuffle the deck here
	}
	
	public Card deal()
    {
        return cards[top++];
		// deal the top card in the deck
	}
	
	// add more methods here if needed

}

