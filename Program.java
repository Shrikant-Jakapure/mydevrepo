package com.app.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Card {
	private String faceName;
	private String suit;

	public Card(String faceName, String suit) {
		this.faceName = faceName;
		this.suit = suit;
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		List<String> validFaceName = getValidFaceName();
		faceName = faceName.toLowerCase();
		if (validFaceName.contains(faceName))
			this.faceName = faceName;
		else
			System.out.println("NOT VALID FACENAME");
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		List<String> validSuit = getValidSuits();
		suit = suit.toLowerCase();
		if (validSuit.contains(suit))
			this.suit = suit;
		else
			System.out.println("NOT VALID SUIT");
	}

	public static List<String> getValidFaceName() {
		return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");

	}

	public static List<String> getValidSuits() {
		return Arrays.asList("hearts", "diamonds", "spades", "clubs");
	}

	@Override
	public String toString() {
		return "Card [faceName=" + faceName + ", suit=" + suit + "]";
	}

}

class Deck {
	private ArrayList<Card> deck;

	public Deck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public Deck() {
		List<String> suit = Card.getValidSuits();
		List<String> faceName = Card.getValidFaceName();
		deck = new ArrayList<Card>();
		for (String s : suit) {

			for (String f : faceName) {
				deck.add(new Card(f, s));
			}
		}

	}

	/*
	 * public Card shuffleDeck() { Random generator = new Random(); int index =
	 * generator.nextInt(deck.size()); return deck.remove(index); }
	 */
	public void shuffleDeck() {
		ArrayList<Card> temp = new ArrayList<Card>();
		while (!deck.isEmpty()) {
			int loc = (int) (Math.random() * deck.size());
			temp.add(deck.get(loc));
			deck.remove(loc);
		}
		deck = temp;
	}

	public int getTotalCards() {
		return deck.size();
	}

	@Override
	public String toString() {
		return "Deck [deck=" + deck + "]";
	}

}

class Player {
	private String name;
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Player(String name) {
		this.name = name;

	}

	void giveCard(Card c) {
		cards.add(c);
	}

	ArrayList<Card> getCards() {
		return cards;
	}

	String printPlayerCards() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + "Has following cards ");

		for (Card card : cards) {
			sb.append(card + "\n");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}

}

/*
 * / prints the hands that a player has in their
 * 
 * class Hand { private Card[] cards; //one player cards. private int[] value; 
 * // value is used later to determine hands strength. Hand(Deck d) { value =
 * new int[6]; //valur use to determine what kind of hands a player has. cards =
 * new Card[5]; for(int x=0;x<5;x++) // draw 5 cards from the deck { cards[x] =
 * d.drawFromDeck(); } int[] ranks = new int[14]; int[] orderedRanks = new
 * int[15]; boolean flush = true,straight = false; // variables to be used to
 * determine pairs, flush, etc. int sameCards = 1, sameCards2 = 1; int
 * largeGroupRank = 0, smallGroupRank = 0; int index = 0; for(int x=0;x<=13;x++)
 * // clear out ranks array. { ranks[x] = 0; } for(int x=0;x<=4;x++) //assign
 * rank to cards in hand. { ranks[cards[x].getRank()]++; } for (int x = 0; x <
 * 4; x++) //Test for similar suit, for possible flush. {  if (
 * cards[x].getSuit() != cards[x+1].getSuit() ) flush = false; } for(int
 * x=13;x>=1;x--) // determine the amount of multiple cards and determine
 * highest face value for possible house. { if(ranks[x] > sameCards) {
 * if(sameCards != 1) { sameCards2 = sameCards; smallGroupRank = largeGroupRank;
 * } else if(ranks[x] > sameCards2)  { sameCards2 = ranks[x]; smallGroupRank =
 * x; } } if(ranks[1] == 1) // run first if ace since it is high value {
 * orderedRanks[index] = 14; index++; } } for(int x=13;x>=2;x--) //we set
 * constraints to 9 becoz a straight cant begin at 10. { if(ranks[x] == 1 &&
 * ranks[x+2] == 1 && ranks[x+3] == 1 && ranks[x+4] == 1) //ranks ascend =
 * straight. { straight = true; break; } } if(ranks[10] == 1 && ranks[12] == 1
 * && ranks[13] == 1 && ranks[1] == 1) // if straight. { straight = true; }
 * for(int x=0;x<=5;x++) { value[x] = 0; //clear the value } if(sameCards == 1)
 * //evalueate of hands. { value[0]=1; value[1]=orderedRanks[0];
 * value[2]=orderedRanks[1]; value[3]=orderedRanks[2]; value[4]=orderedRanks[3];
 * value[5]=orderedRanks[4]; } if(sameCards == 2 & sameCards2 == 1) {
 * value[0]=2; value[1]=largeGroupRank; //Rank of pair value[2]=orderedRanks[0];
 * value[3]=orderedRanks[1]; value[4]=orderedRanks[2]; } if (sameCards==2 &&
 * sameCards2==2) //Two pair { value[0]=3; value[1]=
 * largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; value[2]=
 * largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank;
 * value[3]=orderedRanks[0]; } if (sameCards==3 && sameCards2!=2) { value[0]=4;
 * value[1]= largeGroupRank; value[2]=orderedRanks[0]; value[3]=orderedRanks[1];
 * } if (straight) { value[0]=5; value[1]=orderedRanks[0]; } } } if (flush) {
 * value[0]=6; value[1]=orderedRanks[0]; //Tie determined by ranks of cards
 * value[2]=orderedRanks[1]; value[3]=orderedRanks[2]; value[4]=orderedRanks[3];
 * value[5]=orderedRanks[4]; } if (sameCards==3 && sameCards2==2) { value[0]=7;
 * value[1]=largeGroupRank; value[2]=smallGroupRank; } if (sameCards==4) {
 * value[0]=8; value[1]=largeGroupRank; value[2]=orderedRanks[0]; } } void
 * strength() // display method is to determine and print the strength of the
 * hands. { String result; switch(value[0]) // the highest value[] the strongest
 * the hand. { case 1: result = "high card"; break; case 2: result = "pair of "
 * +Card.rankAsString(value[1])+ "\s"; break; case 3: result= "two pair " +
 * Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]); break; case
 * 4: result= "three of a kind " + Card.rankAsString(value[1]) + "\s"; break;
 * case 5: result= Card.rankAsString(value[1]) + " high straight"; break; case
 * 6: result= "flush"; break; case 7: result= "full house " +
 * Card.rankAsString(value[1]) + " over " +Card.rankAsString(value[2]); break;
 * case 8: result= "four of a kind " + Card.rankAsString(value[1]); break;
 * default: result= "Error, check coding"; } System.out.println(result); //
 * Strength of hand, "pair,three of a kind etc" is printed. } int displayCards()
 * // this method prints the hands that a player has in their hand.\ { for(int
 * x=0;x<5;x++) // print all 5 cards in hand. { System.out.printf("%-16s \t \n",
 * cards[x]); System.out.println("\n"); } int compareTo(Hand that) //Comparing
 * hands. The top hand relates to “this”, bottom hand “that”. { for (int x=0;
 * x<6; x++) { if (this.value[x]>that.value[x]) return 1; else if
 * (this.value[x]<that.value[x]) return -1; } return 0; //Return value
 * translates to top/bottom winner. } }}
 */
public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of players  :: ");
		int size = sc.nextInt();
		Player[] p = new Player[size];
		System.out.println("Enter player name :: ");
		for (int i = 0; i < p.length; i++) {
			p[i] = new Player(sc.next());
		}
		Deck d = new Deck();

		d.shuffleDeck();
		System.out.println(d);

	}

}
