
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import blackjack.BlackjackModel;
import blackjack.GameResult;
import blackjack.HandAssessment;
import deckOfCards.Card;
import deckOfCards.Deck;
import deckOfCards.Rank;
import deckOfCards.Suit;

public class Tests {

	@Test
	public void gameAssessment() {

		BlackjackModel blackjack = new BlackjackModel();

		// SETTING THE PLAYER CARDS
		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.NINE, Suit.CLUBS));

		blackjack.setPlayerCards(cards1);

		// SETTING THE DEALER CARDS
		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));

		blackjack.setDealerCards(cards2);

		System.out.println(BlackjackModel.possibleHandValues(cards1));
		System.out.println(BlackjackModel.possibleHandValues(cards2));

		GameResult mine = blackjack.gameAssessment();
		GameResult truth = GameResult.PLAYER_WON;

		System.out.println("GAME ASSESSMENT: " + mine);
		assertEquals(mine, truth);
	}

	@Test
	public void dealerShouldTakeCard() {

		ArrayList<Card> cards1 = new ArrayList<>();
		BlackjackModel blackjack = new BlackjackModel();
		boolean correctAnswer = true;

		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.SIX, Suit.CLUBS));

		blackjack.setDealerCards(cards1);
		boolean mine = blackjack.dealerShouldTakeCard();
		System.out.println(BlackjackModel.possibleHandValues(cards1));
		System.out.println(Collections.max(BlackjackModel.possibleHandValues(cards1)));
		System.out.println(mine);
		assertEquals(correctAnswer, mine);
	}

	@Test
	public void assessHandTest1() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));

		HandAssessment Mine;
		HandAssessment truth;

		Mine = BlackjackModel.assessHand(cards1);
		truth = HandAssessment.NORMAL;

		System.out.println("Hand Assessment Result 1: " + Mine);
		assertTrue(Mine.equals(truth));

	}

	@Test
	public void assessHandTest14() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.JACK, Suit.CLUBS));
		cards1.add(new Card(Rank.JACK, Suit.CLUBS));

		HandAssessment Mine;
		HandAssessment truth;

		Mine = BlackjackModel.assessHand(cards1);
		truth = HandAssessment.BUST;

		System.out.println("Hand Assessment Result 1: " + Mine);
		assertTrue(Mine.equals(truth));
	}

	@Test
	public void assessHandTest15() {

		ArrayList<Card> cards1 = null;

		HandAssessment Mine;
		HandAssessment truth;

		Mine = BlackjackModel.assessHand(cards1);
		truth = HandAssessment.INSUFFICIENT_CARDS;

		System.out.println("Hand Assessment Result 3331: " + Mine);
		assertTrue(Mine.equals(truth));
	}

	@Test
	public void assessHandTest2() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.JACK, Suit.CLUBS));

		HandAssessment Mine;
		HandAssessment truth;

		Mine = BlackjackModel.assessHand(cards1);
		truth = HandAssessment.INSUFFICIENT_CARDS;

		System.out.println("Hand Assessment Result 2: " + Mine + " and " + truth);
		assertTrue(Mine.equals(truth));

	}

	@Test
	public void assessHandTest3() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.JACK, Suit.CLUBS));

		HandAssessment Mine;
		HandAssessment truth;

		Mine = BlackjackModel.assessHand(cards1);
		truth = HandAssessment.NORMAL;

		System.out.println("Hand Assessment Result 3: " + Mine);
		assertTrue(Mine.equals(truth));
		System.out.println();
		System.out.println();
	}

	@Test
	public void PossibleHandValuestest() {

		Deck deck = new Deck();
		Random rand = new Random();
		deck.shuffle(rand);
		Card c = deck.dealOneCard();
		Card c1 = deck.dealOneCard();
		Card c2 = deck.dealOneCard();
		System.out.print("{ " + c.getRank() + " of " + c.getSuit() + " }");
		System.out.print("{ " + c1.getRank() + " of " + c1.getSuit() + " }");
		System.out.print("{ " + c2.getRank() + " of " + c2.getSuit() + " }");

		ArrayList<Card> cardDealer = new ArrayList<>();
		cardDealer.add(c);
		cardDealer.add(c1);
		cardDealer.add(c2);
		System.out.println();
		System.out.println(BlackjackModel.possibleHandValues(cardDealer));
		System.out.println(BlackjackModel.assessHand(cardDealer));

		System.out.println();
		System.out.println();
	}

	@Test
	public void PossibleHandValuestest1() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.SIX, Suit.CLUBS));
		cards1.add(new Card(Rank.JACK, Suit.CLUBS));
		cards1.add(new Card(Rank.FOUR, Suit.CLUBS));

		ArrayList<Integer> answers = BlackjackModel.possibleHandValues(cards1);
		ArrayList<Integer> truth = new ArrayList<>();

		answers = BlackjackModel.possibleHandValues(cards1);
		truth.add(20);

		System.out.println("HAND VALUE 1: " + answers);
		assertTrue(answers.equals(truth));

	}

	@Test
	public void PossibleHandValuestest22() {

		ArrayList<Card> cards1 = new ArrayList<>();
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));
		cards1.add(new Card(Rank.ACE, Suit.CLUBS));

		ArrayList<Integer> answers = BlackjackModel.possibleHandValues(cards1);
		ArrayList<Integer> truth = new ArrayList<>();

		answers = BlackjackModel.possibleHandValues(cards1);
		truth.add(3);
		truth.add(13);

		System.out.println("HAND VALUE 1: " + answers);
		assertTrue(answers.equals(truth));

	}

	@Test
	public void PossibleHandValuestest2() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.SIX, Suit.CLUBS));
		cards2.add(new Card(Rank.JACK, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(17);

		System.out.println("HAND VALUE 2: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest3() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.JACK, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(11);
		truth.add(21);

		System.out.println("HAND VALUE 3: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest4() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.TWO, Suit.CLUBS));
		cards2.add(new Card(Rank.SEVEN, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(10);
		truth.add(20);

		System.out.println("HAND VALUE 4: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest5() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(3);
		truth.add(13);

		System.out.println("HAND VALUE 5: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest6() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(2);
		truth.add(12);

		System.out.println("HAND VALUE 6: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest7() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.SIX, Suit.CLUBS));
		cards2.add(new Card(Rank.FOUR, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(11);
		truth.add(21);

		System.out.println("HAND VALUE 7: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest8() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.SEVEN, Suit.CLUBS));
		cards2.add(new Card(Rank.FOUR, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(12);

		System.out.println("HAND VALUE 8: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestest9() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));
		cards2.add(new Card(Rank.EIGHT, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(10);
		truth.add(20);

		System.out.println("HAND VALUE 9: " + answers23);
		assertTrue(answers23.equals(truth));

	}

	@Test
	public void PossibleHandValuestes10() {

		ArrayList<Card> cards2 = new ArrayList<>();
		cards2.add(new Card(Rank.SIX, Suit.CLUBS));
		cards2.add(new Card(Rank.SIX, Suit.CLUBS));
		cards2.add(new Card(Rank.JACK, Suit.CLUBS));
		cards2.add(new Card(Rank.ACE, Suit.CLUBS));

		ArrayList<Integer> answers23 = BlackjackModel.possibleHandValues(cards2);
		ArrayList<Integer> truth = new ArrayList<>();

		answers23 = BlackjackModel.possibleHandValues(cards2);
		truth.add(23);

		System.out.println("HAND VALUE 10:" + answers23);
		assertTrue(answers23.equals(truth));

	}

}
