//Vidhi Jain - vj2241
//The Card class allows you to create individeal card objects and compare Cards to one another.
//This class allows you to compare card ranks and card suits as well as output a Card's rank and
//suit in a single String

public class Card implements Comparable<Card>
{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r)
    {
		suit = s;
        rank = r;
        //make a card with suit s and value v
	}
	
	public int compareTo(Card c)
    {
        return rank - c.rank;
		// use this method to compare cards so they 
		// may be easily sorted

	}
    
    public int compareSuitTo(Card c)
    {
        return suit - c.suit;
		// use this method to compare cards so they 
		// may be easily sorted

	}
	
    //returns rank of the card
    public int getRank()
    {
        return rank;
    }
    
	public String toString()
    {
        String suitName = "";
        String rankName = "";
        
        //makes a string of suit name
        if(suit == 1)
        {
            suitName = "Hearts";
        }
        else if(suit == 2)
        {
            suitName = "Diamonds";
        }
        else if(suit == 3)
        {
            suitName = "Clubs";
        }
        else 
        {
            suitName = "Spades";
        }
        
        //makes a string of rank 
        if (rank == 1)
        {
            rankName = "Ace";
        }
        
        else if (rank == 11)
        {
            rankName = "Jack";
        }
        
        else if (rank == 12)
        {
            rankName = "Queen";
        }
        
        else if (rank == 13)
        {
            rankName = "King";
        }
        
        else
        {
           rankName = Integer.toString(rank);
        }
       
        //concatenates the rank and suit strings
        return rankName + " of " + suitName;
        
		// use this method to easily print a Card object
	}
    
	// add some more methods here if needed

}

