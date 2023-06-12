package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {
	private ArrayList<Card> dealerCards;
	private ArrayList<Card> playerCards;
	private Deck deck;

	/**
	 * returns a deep copy of the dealer's card so that when someone calls the
	 * getter, it won't give them the ability to modify the dealer cards
	 * 
	 * @return : An Array list of Cards for the dealer
	 */
	public ArrayList<Card> getDealerCards() {
		ArrayList<Card> deepDealerCopy = new ArrayList<>();
		for (Card card : dealerCards) {
			deepDealerCopy.add(card);
		}
		return deepDealerCopy;

	}

	/**
	 * returns a deep copy of the player's card so that when someone calls the
	 * getter, it won't give them the ability to modify the player cards
	 * 
	 * @return An Array list of Cards for the player
	 */
	public ArrayList<Card> getPlayerCards() {
		ArrayList<Card> deepPlayerCopy = new ArrayList<>();
		for (Card card : playerCards) {
			deepPlayerCopy.add(card);
		}
		return deepPlayerCopy;
	}

	/**
	 * returns a deep copy of the players card to avoid privacy leaks
	 * 
	 * @parameter : an Array list of Card containing dealerCards
	 */
	public void setDealerCards(ArrayList<Card> dealerCards) {
		ArrayList<Card> deepDealerCopy = new ArrayList<>();
		for (Card card : dealerCards) {
			deepDealerCopy.add(card);
		}
		this.dealerCards = deepDealerCopy;
	}

	/**
	 * returns a deep copy of the players card
	 * 
	 * @parameter : an Array list of Card containing playerCards
	 */
	public void setPlayerCards(ArrayList<Card> playerCards) {
		ArrayList<Card> deepPlayerCopy = new ArrayList<>();
		for (Card card : playerCards) {
			deepPlayerCopy.add(card);
		}
		this.playerCards = playerCards;
	}

	/**
	 * This method will assign a new instance of the Deck class to the deck
	 * variable, and will then shuffle the deck, passing the parameter (random)
	 * along to the deck's shuffle method.
	 * 
	 * @param : Random generator
	 */
	public void createAndShuffleDeck(Random random) {
		deck = new Deck();
		deck.shuffle(random);

	}

	/*
	 * Methods to deal the initial two cards to the dealer. It instantiates the
	 * dealerCards and then will deal two cards from the deck and add them to that
	 * dealerCards list.
	 */
	public void initialDealerCards() {
		dealerCards = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			dealerCards.add(deck.dealOneCard());
		}
	}

	/*
	 * Methods to deal the initial two cards to the player It instantiates the
	 * playerCards and then will deal two cards from the deck and add them to that
	 * dealerCards list.
	 */
	public void initialPlayerCards() {

		playerCards = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			playerCards.add(deck.dealOneCard());
		}
	}

	/*
	 * This methods deals just one card to the player.
	 */
	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());

	}

	/*
	 * This methods deals just one card to the dealer.
	 */
	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());

	}

	/**
	 * This method will evaluate the hand in question and return a very short
	 * ArrayList that contains either one or two Integers, representing the value(s)
	 * that could be assigned to that hand.
	 * 
	 * @param : An Array List of hand used to asses the players and dealers hands
	 * @return : An Array list of the sum of integer in players and dealers hands
	 */
	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		ArrayList<Integer> cards = new ArrayList<>();
		// keeps track of the amount of Ace there are
		int aceAmount = 0;
		// Sums up the total in hand
		int total = 0;

		// for ever ace there is in a payer's or dealer's hand the ace amount
		// grows by one

		for (Card card : hand) {
			if (card.getRank() == Rank.ACE) {
				aceAmount++;
			}
		}
		// if there are no ace in a hand, simply get the value of each card in hand
		// and return the sum of all the cards.

		if (aceAmount == 0) {
			for (Card card : hand) {
				total += card.getRank().getValue();
			}
			cards.add(total);
			return cards;
		} else {

			// for every card not including ace calculate the sum like done previously
			for (Card card : hand) {
				if (card.getRank() != Rank.ACE) {
					total += card.getRank().getValue();
				}
			}

			/*
			 * In case of presence of Ace in hand we have to assess the better option of
			 * counting the ace.
			 * 
			 * if the total of cards in hand(not including ace) added to Ace(counting as
			 * eleven - for every Ace amount minus 1 - since we are already adding 11 for
			 * one ace) is greater than 21(BUST), we only want to return the total summed up
			 * counting ace as 1.
			 */
			int aceIsEleven = total + 11 + (aceAmount - 1);
			int aceIsOne = total + (1 * aceAmount);
			if (aceIsEleven > 21) {
				cards.add(aceIsOne);
				return cards;
			}

			/*
			 * if when we calculate the sum of hands and we count ACE as 11 and the player
			 * or the dealer does not bust then we want to return both values calculated
			 * (ace as 1 and ace as 11).
			 */
			cards.add(aceIsOne);
			cards.add(aceIsEleven);
			return cards;

		}
	}

	/**
	 * This method will assess the hand and return one of the four HandAssessment
	 * constants, as follows: INSUFFICIENT_CARDS -- if the hand is null or contains
	 * fewer than 2 cards NATURAL_BLACKJACK -- if the hand represents a "Natural
	 * Blackjack" BUST -- if the hand's value is over 21 NORMAL -- if none of the
	 * categories above apply
	 * 
	 * @param : An Array List of hand used to asses the players and dealers hands
	 * @return : Hand Assessment characterizing given blackJack hand
	 */
	public static HandAssessment assessHand(ArrayList<Card> hand) {

		if (hand == null) {
			return HandAssessment.INSUFFICIENT_CARDS;
		}

		ArrayList<Integer> hands = possibleHandValues(hand);
		int size = hand.size();

		if (size < 2) {
			return HandAssessment.INSUFFICIENT_CARDS;
		} else if (size == 2 && hands.contains(21)) {
			return HandAssessment.NATURAL_BLACKJACK;

		} else if (Collections.max(hands) > 21) {
			return HandAssessment.BUST;

		} else {
			return HandAssessment.NORMAL;

		}

	}

	/**
	 * This method will look at the playerCards and the dealerCards and determine
	 * the outcome of the game, returning one of the GameResult constants
	 * (PLAYER_WON, PLAYER_LOST, PUSH, or NATURAL_BLACKJACK).
	 * 
	 * @return : Game result used to determine who wins and losses and the amount of
	 *         money lost or won.
	 */
	public GameResult gameAssessment() {

		HandAssessment player = assessHand(playerCards);
		HandAssessment dealer = assessHand(dealerCards);

		ArrayList<Integer> playerValue = possibleHandValues(playerCards);
		ArrayList<Integer> dealerValue = possibleHandValues(dealerCards);

		// if the player and the dealer has blackjack
		if (player.equals(HandAssessment.NATURAL_BLACKJACK) && !dealer.equals(HandAssessment.NATURAL_BLACKJACK)) {
			return GameResult.NATURAL_BLACKJACK;

		} else if (player.equals(HandAssessment.NATURAL_BLACKJACK) && dealer.equals(HandAssessment.NATURAL_BLACKJACK)) {
			return GameResult.PUSH;

		} else {
			// if the player busts
			if (player.equals(HandAssessment.BUST)) {
				return GameResult.PLAYER_LOST;
				// if the dealer busts and the player does not
			} else if (!player.equals(HandAssessment.BUST) && dealer.equals(HandAssessment.BUST)) {
				return GameResult.PLAYER_WON;
			} else {
				// if neither busts and the dealer has less than the plater, player wins and
				// vice versa
				if (!player.equals(HandAssessment.BUST) && !dealer.equals(HandAssessment.BUST)
						&& (Collections.max(playerValue) > Collections.max(dealerValue))) {
					return GameResult.PLAYER_WON;
				} else if (Collections.max(playerValue) < Collections.max(dealerValue)) {
					return GameResult.PLAYER_LOST;
				} else {
					return GameResult.PUSH;
				}
			}
		}

	}

	/*
	 * This method will look at the dealerCards to determine whether or not the
	 * dealer should take another card during her turn, returning true if the dealer
	 * should take another card and false otherwise.
	 */
	public boolean dealerShouldTakeCard() {
		ArrayList<Integer> dealerHand = possibleHandValues(dealerCards);

		int aceAmount = 0;

		for (Card card : dealerCards) {
			if (card.getRank() == Rank.ACE) {
				aceAmount++;
			}
		}
		// maximum hand value is <= to 16, dealer keep taking card..
		if (Collections.max(dealerHand) <= 16) {
			return true;
			// maximum possible hand value is >= to 18, dealer stop taking card..
		} else if (Collections.max(dealerHand) >= 18) {
			return false;
			// Ace is present and total hand for the dealer is 7/17, dealer keep taking
			// card..
		} else if (aceAmount > 0 && (dealerHand.contains(7) && dealerHand.contains(17))) {
			return true;
			// total hand value is 17 and not 7, stop taking more card..
		} else {
			return false;
		}

	}

}
