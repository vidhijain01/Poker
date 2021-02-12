//Vidhi Jain - vj2241
//The Game class is where the game itself is played. It creates a player and a deck of cards and deals a 
//hand to the player. It asks the player how much they would like to bet, if they would like to replace 
//any cards, and checks their cards to see what their winning hand is. Based on their winning hand,
//it will adjust the player's bankroll and output their winning hand as well as their updated bankroll;

import java.util.*; 
// add your own banner here

public class Game 
{
	
	private Player p;
	private Deck cards;
	// you'll probably need some more here
	
	
	public Game(String[] testHand)
    {
        p = new Player();
        cards = new Deck();
        String tempSuit = "";
        String tempNum = "";
        int suitInt = 0;
        int numInt = 0;
        
        for(int i=0; i<testHand.length; i++)
        {
            tempSuit = testHand[i].substring(0,1);
            tempNum = testHand[i].substring(1);
            if(tempSuit.equals("h"))
            {
                suitInt = 1;
            }
            else if(tempSuit.equals("d"))
            {
                suitInt = 2;
            }
            else if(tempSuit.equals("c"))
            {
                suitInt = 3;
            }
            else 
            {
                suitInt = 4;
            }
            
            numInt = Integer.parseInt(tempNum);
            
            Card temp = new Card(suitInt, numInt);
            p.addCard(temp);
        }
        
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		
		
	}
	
	public Game()
    {
        p = new Player();
        cards = new Deck();
		// This no-argument constructor is to actually play a normal game
		
	}
	
	public void play()
    {
        //intro to the game
        System.out.println("Welcome to the game of poker!");
        System.out.println("--------------------------------");
        System.out.println("\nYour starting bankroll is : " + p.getBankroll());
        
        //shuffle the cards
        cards.shuffle();
        
        Scanner s = new Scanner(System.in);
        
        //get the bet
        double x = 0;
        System.out.println("How much would you like to bet?");
        x = s.nextDouble();
        
        //check that the bet is valid
        while( x>5 || x<1 )
        {
            System.out.println("Sorry that's an invalid bet amount!");
            System.out.println("How much would you like to bet?");
            x = s.nextDouble();
            
        }
            
        p.bets(x);
        
        //create the player's hand
        for(int i=0; i<5; i++)
        {  
            if(p.getHand().size()<5)
            {
                p.addCard(cards.deal());
            }
            
        }
        
        //output the player's cards
        System.out.println("Your cards: ");
        System.out.println("----------------------");
        for(int i=0; i<5; i++)
        {
            System.out.println(p.getHand().get(i));
        }
        System.out.println("");
        
        //ask the player if they would like to replace cards and get indices
        System.out.println("How many (if any) cards would you like to replace?");
        int numReplace = s.nextInt();
        
        ArrayList<Integer> replace = new ArrayList<Integer>();
        for(int i = 0; i<numReplace; i++)
        {
            System.out.println("Pick which index to replace: 0 1 2 3 4");
            replace.add(s.nextInt());
            System.out.println("It will be removed.\n");
            
        }
        
        
       Collections.sort(replace);
        
       //replace with new cards
       for(int i=numReplace-1; i>=0; i--)
       {
           p.removeCard(p.getHand().get(replace.get(i)));
           p.addCard(cards.deal());
       }
        
        //output player's cards again
        System.out.println("\nYour cards: ");
        System.out.println("----------------------");
        for(int i=0; i<5; i++)
        {
            System.out.println(p.getHand().get(i));
        }
        System.out.println("");
        
        //decide what the player's hand is and adjust bankroll
        String move = checkHand(p.getHand());
        
        System.out.println("\nYour hand is: " + move);
        
        if(move.equals("Royal Flush"))
        {
            p.winnings(250);
        }
        else if(move.equals("Straight Flush"))
        {
            p.winnings(50);
        }
        else if(move.equals("Four Of A Kind"))
        {
            p.winnings(25);
        }
        else if(move.equals("Full House"))
        {
            p.winnings(6);
        }
        else if(move.equals("Flush"))
        {
            p.winnings(5);
        }
        else if(move.equals("Straight"))
        {
            p.winnings(4);
        }
        else if(move.equals("Three Of A Kind"))
        {
            p.winnings(3);
        }
        else if(move.equals("Two Pairs"))
        {
           p.winnings(2);
        }
        else if(move.equals("One Pair"))
        {
            p.winnings(1);
        }
        else
        {
            p.winnings(0);
        }
        
        //output player's bankroll 
        System.out.println("Your current bankroll is: " + p.getBankroll());
        System.out.println("\nThank you for playing!");
        
        s.close();
		// this method should play the game	
	}
	
	public String checkHand(ArrayList<Card> hand)
    {
        //check each hand method in order of hierarchy
        if(royalFlush(hand)==true)
        {
            return "Royal Flush";
        }
        else if(straightFlush(hand)==true)
        {
            return "Straight Flush";
        }
        else if(fourOfAKind(hand)==true)
        {
            return "Four Of A Kind";
        }
        else if(fullHouse(hand)==true)
        {
            return "Full House";
        }
        else if(flush(hand)==true)
        {
            return "Flush";
        }
        else if(straight(hand)==true)
        {
            return "Straight";
        }
        else if(threeOfAKind(hand)==true)
        {
            return "Three Of A Kind";
        }
        else if(twoPairs(hand)==true)
        {
            return "Two Pairs";
        }
        else if(onePair(hand)==true)
        {
            return "One Pair";
        }
        else
        {
            return "No Pair";
        }
        
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		
	}
	
    //check if there is one pair of cards with the same rank
    private boolean onePair(ArrayList<Card> hand)
    {
        int same = 0;
        for(int i=0; i<hand.size(); i++)
        {
            for(int j=0; j<hand.size(); j++)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    same = hand.get(i).compareTo(hand.get(j));
                    if (same == 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //check if there are two pairs of cards with the same rank
    private boolean twoPairs(ArrayList<Card> hand)
    {
        int same = 0;
        int count = 0;
        for(int i=0; i<hand.size(); i++)
        {
            for(int j=hand.size()-1; j>i; j--)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    same = hand.get(i).compareTo(hand.get(j));
                    if (same == 0)
                    {
                        count++;
                        if(count == 2)
                        {  
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    //check if there are 3 cards of the same rank
    private boolean threeOfAKind(ArrayList<Card> hand)
    {
        int same = -1;
        int count = 1;
        for(int i=0; i<hand.size(); i++)
        {
            for(int j=0; j<hand.size(); j++)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    same = hand.get(i).compareTo(hand.get(j));
                    if (same == 0)
                    {
                        count++;
                        if(count == 3)
                        {  
                            return true;
                        }
                    }
                }
            }
            count = 1;
        }
        return false;
    }
    
    //check if all 5 cards are sequential in rank
    private boolean straight(ArrayList<Card> hand)
    {
        //sorting array list
        for (int i = 0; i < hand.size(); i++)
        {
             for (int j = hand.size() - 1; j > i; j--) 
             {
                  if (hand.get(i).getRank() > hand.get(j).getRank()) 
                  {
                      Card temp = hand.get(i);
                      hand.set(i,hand.get(j)) ;
                      hand.set(j,temp);
                  }
              }
        }
        
        int same = 0;
        int count = 1;
        
        if(hand.get(0).getRank()==1)
        {
            if(hand.get(1).getRank()==10 && hand.get(2).getRank()==11 && hand.get(3).getRank()==12 
               && hand.get(4).getRank()==13)
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        
        for(int i=0; i<hand.size()-1; i++)
        {
           same = hand.get(i).compareTo(hand.get(i+1));
           if (same == 1 || same == -1)
           {
                count ++;
           }
        }
        
        if(count == 5)
        {
            return true;
        }
        
        return false;
        
     }
    
    //check if all 5 cards are of the same suit
    private boolean flush(ArrayList<Card> hand)
    {
        int same = 0;
        for(int j=1; j<hand.size(); j++)
        {
             same = hand.get(0).compareSuitTo(hand.get(j));
             if (same != 0)
             {
                 return false;
             }
        }
        return true;
    }
    
    //check if there are 3 cards of the same rank and the other 2 cards of the same rank
    private boolean fullHouse(ArrayList<Card> hand)
    {
        
        if(threeOfAKind(hand)!=true)
        {
            return false;
        }
        else
        {
            int same = 0;
            int count = 1;
            for(int i=0; i<hand.size(); i++)
            {
                for(int j=0; j<hand.size(); j++)
                {
                    if(i==j)
                    {
                        continue;
                    }
                    else
                    {
                        same = hand.get(i).compareTo(hand.get(j));
                        if (same == 0)
                        {
                            count++;
                            if (count == 3)
                            {
                                break;
                            }
                                
                        }
                    }
                }
                if(count == 2)
                {
                    return true;
                }
                count = 1;
            }
        }
        return false;
        
    }
    
    //check if there are 4 cards of the same rank
    private boolean fourOfAKind(ArrayList<Card> hand)
    {
        int same = -1;
        int count = 1;
        for(int i=0; i<hand.size(); i++)
        {
            for(int j=0; j<hand.size(); j++)
            {
                if(i==j)
                {
                    continue;
                }
                else
                {
                    same = hand.get(i).compareTo(hand.get(j));
                    
                    if (same == 0)
                    {
                        count++;
                        if(count == 4)
                        {  
                            return true;
                        }
                    }
                }
            }
            count = 1;
            
        }
        return false;
        
    }
    
    //check if all 5 cards are sequentill in rank and of the same suit
    private boolean straightFlush(ArrayList<Card> hand)
    {
        if(straight(hand)==true && flush(hand)==true)
        {
            return true;
        }
        return false;
    }
    
    //check for the highest possible hand of ace, king, queen, jack, 10 of the same suit
    private boolean royalFlush(ArrayList<Card> hand)
    {
        if(flush(hand) != true)
        {
            return false;
        }
        
        //sorting array
        for (int i = 0; i < hand.size(); i++)
        {
             for (int j = hand.size() - 1; j > i; j--) 
             {
                  if (hand.get(i).getRank() > hand.get(j).getRank()) 
                  {
                      Card temp = hand.get(i);
                      hand.set(i,hand.get(j)) ;
                      hand.set(j,temp);
                  }
              }
        }
        
        if(hand.get(0).getRank()==1)
        {
            if(hand.get(1).getRank()==10)
            {
                if(hand.get(2).getRank()==11)
                {
                    if(hand.get(3).getRank()==12)
                    {
                        if(hand.get(4).getRank()==13)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
        
    
    
	// you will likely want many more methods here
	// per discussion in class
}

