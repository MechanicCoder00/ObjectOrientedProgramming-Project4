package blackjackpackage;

import java.util.ArrayList;
import java.util.List;

class Card
{
	private int cardValue;
	private String cardSuit;
	private String cardname;
	
	public int getCardValue()
	{
		return cardValue;
	}
	
	public void setCardValue(int cardValue)
	{
		this.cardValue = cardValue;
	}
	
	public String getCardSuit()
	{
		return cardSuit;
	}
	
	public void setCardSuit(String cardSuit)
	{
		this.cardSuit = cardSuit;
	}
	
	public String getCardName() {
		return cardname;
	}

	public void setCardName(String cardname) {
		this.cardname = cardname;
	}
	
	public void aceHighToLow(Object o) 
	{
		String suit = getCardSuit();	
		new RankAL(suit);
	}
	
	@Override
	public String toString()  
	{ 
		return((getCardName())+(getCardSuit()));
	}
}

class RankAH extends Card
{
	public RankAH(String cardSuit) 
	{ 
		setCardValue(11);
		setCardSuit(cardSuit);
		setCardName("A");
	}
}
class RankAL extends Card
{
	public RankAL(String cardSuit) 
	{ 
		setCardValue(1);
		setCardSuit(cardSuit);
		setCardName("A");
	}
}
class Rank2 extends Card
{
	public Rank2(String cardSuit) 
	{ 
		setCardValue(2);
		setCardSuit(cardSuit);
		setCardName("2");
	}
}
class Rank3 extends Card
{
	public Rank3(String cardSuit) 
	{ 
		setCardValue(3);
		setCardSuit(cardSuit);
		setCardName("3");
	}
}
class Rank4 extends Card
{
	public Rank4(String cardSuit) 
	{ 
		setCardValue(4);
		setCardSuit(cardSuit);
		setCardName("4");
	}
}
class Rank5 extends Card
{
	public Rank5(String cardSuit) 
	{ 
		setCardValue(5);
		setCardSuit(cardSuit);
		setCardName("5");
	}
}
class Rank6 extends Card
{
	public Rank6(String cardSuit) 
	{ 
		setCardValue(6);
		setCardSuit(cardSuit);
		setCardName("6");
	}
}
class Rank7 extends Card
{
	public Rank7(String cardSuit) 
	{ 
		setCardValue(7);
		setCardSuit(cardSuit);
		setCardName("7");
	}
}
class Rank8 extends Card
{
	public Rank8(String cardSuit) 
	{ 
		setCardValue(8);
		setCardSuit(cardSuit);
		setCardName("8");
	}
}
class Rank9 extends Card
{
	public Rank9(String cardSuit) 
	{ 
		setCardValue(9);
		setCardSuit(cardSuit);
		setCardName("9");
	}
}
class Rank10 extends Card
{
	public Rank10(String cardSuit) 
	{ 
		setCardValue(10);
		setCardSuit(cardSuit);
		setCardName("10");
	}
}
class RankJ extends Card
{
	public RankJ(String cardSuit) 
	{ 
		setCardValue(10);
		setCardSuit(cardSuit);
		setCardName("J");
	}
}
class RankQ extends Card
{
	public RankQ(String cardSuit) 
	{ 
		setCardValue(10);
		setCardSuit(cardSuit);
		setCardName("Q");
	}
}
class RankK extends Card
{
	public RankK(String cardSuit) 
	{ 
		setCardValue(10);
		setCardSuit(cardSuit);
		setCardName("K");
	}
}

class Deck 
{
	static List<Object> CreateDeck()
	{
		List<Object> deck1 = new ArrayList<Object>();
	
		for(int i=0;i<4;i++)
		{
	        String suit = null;
	        String hearts = "\u2665";
	        String diamonds = "\u2666";
	        String clubs = "\u2663";
	        String spades = "\u2660";
	        
	        switch (i) 
	        { 
		        case 0: 
		        	suit = hearts;
//		            suit = " of Hearts"; 
		            break; 
		        case 1: 
		        	suit = diamonds;
//		        	suit = " of Diamonds"; 
		            break; 
		        case 2: 
		        	suit = clubs;
//		        	suit = " of Clubs"; 
		            break; 
		        case 3: 
		        	suit = spades;
//		        	suit = " of Spades"; 
		            break; 
	        }
	        
	        deck1.add(new RankAH(suit));
			deck1.add(new Rank2(suit));
			deck1.add(new Rank3(suit));
			deck1.add(new Rank4(suit));
			deck1.add(new Rank5(suit));
			deck1.add(new Rank6(suit));
			deck1.add(new Rank7(suit));
			deck1.add(new Rank8(suit));
			deck1.add(new Rank9(suit));
			deck1.add(new Rank10(suit));
			deck1.add(new RankJ(suit));
			deck1.add(new RankQ(suit));
			deck1.add(new RankK(suit));
		}
		
		return deck1;
	}
}

class BlackJackDeck extends Deck
{
	static List<Object> CreateBlackJackDeck()
	{
		List<Object> bjdeck = new ArrayList<Object>();
		List<Object> deck1 = new ArrayList<Object>();
		
		deck1 = CreateDeck();
		int decksize=deck1.size();
		
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<decksize;j++)
			{
				bjdeck.add(deck1.get(j));
			}
		}
		return bjdeck;
	}
	
	public static int getCardValue(Object o)
	{
		return ((Card) o).getCardValue();
	}
	
	public static Object aceHighToLow(Object o) 
	{
		String suit = ((Card) o).getCardSuit();	
		return new RankAL(suit);
	}
}