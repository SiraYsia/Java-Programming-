package deckOfCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cardss;

	/*
	 * A constructor initializing the usual 52 cards found in a deck in order from
	 * the Ace of Spades to the King of Diamonds.
	 */
	public Deck() {
		int rank = Rank.values().length;
		int suit = Suit.values().length;

		this.cardss = new ArrayList<Card>(rank * suit);
		for (Suit cardSuit : Suit.values()) {
			for (Rank cardRank : Rank.values()) {
				this.cardss.add(new Card(cardRank, cardSuit));

			}

		}
	}

	/*
	 * Shuffles the card randomly generating the exact same shuffles over and over.
	 */
	public void shuffle(Random randomNumberGenerator) {
		Collections.shuffle(cardss, randomNumberGenerator);
	}

	/*
	 * This method will remove one card from the front of the list (index 0) and
	 * return it.
	 */
	public Card dealOneCard() {
		Card get = cardss.get(0);
		cardss.remove(0);
		return get;

	}
}
