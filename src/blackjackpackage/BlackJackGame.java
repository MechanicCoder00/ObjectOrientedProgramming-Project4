//****Project 4 / Blackjack Game****
//
//Author: Scott Tabaka
//Instructor: Steve Riegerix
//Class: CMPSCI 2700(Fall 2018)
//Due Date: October 28, 2018

/*
Please enable unicode for this to work properly.

How to enable unicode in eclipse 
1.Select the "window" tab at the top. 
2.Select "preferences".  
3.Click on the triangle next to "General". 
4.Click on "Workspace" subcategory.
5.At the bottom of the Workspace window there is a option for text file encoding. Click on "other".
6.Select "UTF-8"
7.Click "Apply and Close"
 */

package blackjackpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class BlackJackGame 
{
	private static int playermoney = 100;
	private static int playerbet = 0;
	private static int playerwincount = 0;
	private static int dealerwincount = 0;
	private static int tiecount = 0;
	private static int gamecount = 0;
	
	final static int limit = 21;
	static List<Object> bjdeck = new ArrayList<Object>();
	static List<Object> playerhand = new ArrayList<Object>();
	static List<Object> dealerhand = new ArrayList<Object>();
	static Scanner userInput = new Scanner(System.in);
	
	public static void displayEndGameData()
	{
		System.out.println("Hands Played:" + getGamecount());
		System.out.println("# Player Wins:" + getPlayerwincount());
		System.out.println("# Dealer Wins:" + getDealerwincount());
		System.out.println("# Ties:" + getTiecount());
		System.out.println("Total $ Won/Lost: $" + (getPlayermoney() - 100));
	}
	
	public static void clearPlayerbet()
	{
		BlackJackGame.playerbet = 0;
	}
	
	public static int getPlayerbet()
	{
		return BlackJackGame.playerbet;
	}

	public static void setPlayerbet()
	{
		BlackJackGame.playerbet += 2;
	}
	
	public static int getPlayerwincount()
	{
		return playerwincount;
	}

	public static void setPlayerwincount()
	{
		playerwincount++;
	}

	public static int getDealerwincount()
	{
		return dealerwincount;
	}

	public static void setDealerwincount()
	{
		dealerwincount++;
	}

	public static int getTiecount()
	{
		return tiecount;
	}

	public static void setTiecount()
	{
		tiecount++;
	}

	public static int getGamecount()
	{
		return gamecount;
	}

	public static void setGamecount()
	{
		gamecount++;
	}

	public static int getPlayermoney()
	{
		return playermoney;
	}

	public static void setPlayermoney(int money)
	{
		playermoney += money;
	}
	
	public static void PlayerBet()
	{
		setPlayerbet();
		setPlayermoney(-2);
		System.out.println("-$2 bet");
	}
	public static void PlayerBetReturn()
	{
		setPlayermoney(getPlayerbet());
		System.out.println("+$" + (getPlayerbet()));
	}
	public static void PlayerWinMoney()
	{
		setPlayermoney(getPlayerbet()*2);
		System.out.println("+$" + (getPlayerbet()*2));
	}
	
	public static void DisplayMoney()
	{
		System.out.println("Player Money $" + getPlayermoney());
	}
	
	public static void StartGame()
	{
		setGamecount();
		DisplayMoney();
		PlayerBet();
		DisplayMoney();
		DealCardsAll();
		boolean bust = PlayerHitOrStand();
		if(!bust)
		{
			boolean dlrbust = DealerHitOrStand();
			if(!dlrbust)
			{
				WinCheck();
				clearPlayerbet();
			}
			else
			{
				System.out.println("Player Wins\n");
				PlayerWinMoney();
				DisplayMoney();
				clearPlayerbet();
				setPlayerwincount();
			}
		}
		else
		{
		System.out.println("Dealer Wins\n");
		DisplayMoney();
		clearPlayerbet();
		setDealerwincount();
		}
	}
	
	public static void WinCheck()
	{
		if(CalculateHand(dealerhand) < CalculateHand(playerhand))
		{
			System.out.println("Player Wins\n");
			PlayerWinMoney();
			DisplayMoney();
			setPlayerwincount();
		}
		else if(CalculateHand(playerhand) < CalculateHand(dealerhand))
		{
			System.out.println("Dealer Wins\n");
			DisplayMoney();
			setDealerwincount();
		}
		else
		{
			System.out.println("Tie\n");
			PlayerBetReturn();
			DisplayMoney();
			setTiecount();
		}
	}
	
	public static void Reshuffle()
	{
		System.out.println("Reshuffling... \n");
		bjdeck.clear();
		bjdeck = BlackJackDeck.CreateBlackJackDeck();
		Collections.shuffle(bjdeck);
	}
	
	public static void DealCardsAll()
	{
		if(bjdeck.size()<=75)
		{
			Reshuffle();
		}
		playerhand.add(DealCard());
		dealerhand.add(DealCard());
		playerhand.add(DealCard());
		dealerhand.add(DealCard());
	}
	
	public static Object DealCard()
	{
		Object tempcard = bjdeck.get(0);	
		bjdeck.remove(0);
		return tempcard;
	}
	
	public static void DisplayPlayerHand()
	{
		System.out.print("\nPlayer's hand: ");
		System.out.print(playerhand);
		int temp = CalculateHand(playerhand);
		System.out.println("Player Total Hand Value: " + temp);
	}
	
	public static void DisplayDealerHand()
	{
		System.out.print("Dealer's hand: ");
		System.out.print(dealerhand);
		int temp = CalculateHand(dealerhand);
		System.out.println("Dealer Total Hand Value : " + temp);
	}
	
	public static int CalculateHand(List<Object> tempobjarray)
	{
		int temptotal = 0;
		
		for(int i=0;i<tempobjarray.size();i++)
		{
			temptotal += BlackJackDeck.getCardValue(tempobjarray.get(i));
		}
		return temptotal;
	}
	
	public static void ClearHands()
	{
		playerhand.clear();
		dealerhand.clear();
	}
	
	public static boolean AceCheck(List<Object> tempobjarray)
	{
		boolean acepresent = false;
		
		for(int i=0;i<tempobjarray.size();i++)
		{
			if(BlackJackDeck.getCardValue(tempobjarray.get(i))== 11)
			{
				acepresent = true;
				Object tempobject = tempobjarray.get(i);
				tempobject = BlackJackDeck.aceHighToLow(tempobject);
				tempobjarray.set(i, tempobject);
				break;
			}
		}
		return acepresent;
	}
	
	public static boolean BustCheck(List<Object> tempobjarray)
	{
		boolean bust = false;
		boolean acepresent = false;
		
		if(CalculateHand(tempobjarray)> limit)
		{
			acepresent = AceCheck(tempobjarray);
			if(acepresent)
			{
				bust = false;
			}
			else
			{
				bust = true;
			}
		}
		else 
		{
			bust = false;	
		}
		return bust;
	}
	
	public static boolean DealerHitOrStand()
	{
		boolean repeatloop1 = true;
		boolean bust = false;

		while(repeatloop1 == true)
		{
			BustCheck(dealerhand);
			DisplayPlayerHand();
			DisplayDealerHand();
			
			if(CalculateHand(dealerhand) < 17)
			{
				System.out.println("Dealer Hits");
				dealerhand.add(DealCard());
				bust = BustCheck(dealerhand);
				
				if(bust == false)
				{
					repeatloop1 = true;
				}
				else
				{
					repeatloop1 = false;
					DisplayPlayerHand();
					DisplayDealerHand();
					System.out.println("Dealer Busts!");
				}
			}
			else
			{
				System.out.println("Dealer Stands");
				repeatloop1 = false;
			}
		}
		return bust;
	}
	
	public static boolean Doubledown()
	{
		char choice = 0;
		boolean repeatddloop = true;
		boolean repeatloop = true;
		int temp = CalculateHand(playerhand);
		
		if(temp <= 11 && temp >= 9)
		{
			while(repeatddloop == true)
			{
				repeatddloop = false;
				DisplayPlayerHand();
				System.out.print("\nDouble Down?(y/n):");
				choice = userInput.next().charAt(0);
				
				if (choice == 'y' || choice == 'Y')
				{
					PlayerBet();
					playerhand.add(DealCard());
					repeatloop = false;
				}
				else if (choice == 'n' || choice == 'N')
				{
					repeatloop = true;
				}
				else
				{
					repeatddloop = true;
				}
			}			
		}
		return repeatloop;
	}
	
	public static boolean PlayerHitOrStand()
	{
		char hit = 0;
		boolean repeatloop = true;
		boolean bust = false;
		
		repeatloop = Doubledown();
		
		while(repeatloop == true)
		{
			BustCheck(playerhand);
			DisplayPlayerHand();		
			System.out.print("Hit or Stand? (h/s):");
			hit = userInput.next().charAt(0);	
	
			if(hit == 'h' || hit == 'H')
			{
				playerhand.add(DealCard());
				bust = BustCheck(playerhand);
				if(bust == false)
				{
					repeatloop = true;
				}
				else
				{
					repeatloop = false;
					DisplayPlayerHand();
					System.out.print("Bust! ");
				}
			}
			else if (hit == 's' || hit == 'S')
			{
				repeatloop = false;
			}
			else
			{
				repeatloop = true;
				System.out.print("Please try again\n");
			}
		}
		return bust;
	}
	
	public static boolean keepPlaying()
	{
		char choice = 0;
		boolean repeatloop = true;
		boolean playercontinuechoice = false;
		
		while(repeatloop == true)
		{
			repeatloop = false;
			
			System.out.print("\nWould you like to play again?(y/n)");
			choice = userInput.next().charAt(0);
			
			if (choice == 'y' || choice == 'Y')
			{
				playercontinuechoice = true;
			}
			else if (choice == 'n' || choice == 'N')
			{
				playercontinuechoice = false;
			}
			else
			{
				repeatloop = true;
			}
		}
		ClearHands();
		System.out.println("");
		return playercontinuechoice;
	}
	
	public static void ShowDeckInfo()
	{
		System.out.println(bjdeck.size());
		System.out.println(bjdeck);
	}
	
	public static void main(String[] args)
	{
		Boolean keepPlaying = true;
		
		bjdeck = BlackJackDeck.CreateBlackJackDeck();
		Collections.shuffle(bjdeck);
			
		while(keepPlaying == true)
		{
			StartGame();
			keepPlaying = keepPlaying();
		}
		userInput.close();
		displayEndGameData();
	}
}
