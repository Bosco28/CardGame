package Model;

import Model.Card.Card;
import Model.Card.Kill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boscp on 2017-05-24.
 */
public class CardManager {

    /** Number of Kill card in a deck */
    public static final int NUM_OF_KILL = 10;
    /** A list of the remaining cards */
    private List<Card> cards;
    /** An instance of this Model.CardManager */
    private static CardManager instance;

    /**
     * Constructor
     */
    private CardManager() {
        instance = this;
        cards = new ArrayList<>();
        checkRunOutOfCards();
    }

    public static CardManager getInstance() {
        if (instance == null)
            new CardManager();
        return instance;
    }

    /**
     * add a deck of card to cards when initialize or run out of cards
     */
    private void checkRunOutOfCards() {
        if (cards.size() == 0) {
            for (int i = 0; i < NUM_OF_KILL; i++) {
                addCard(new Kill());
            }
        }
    }

    /**
     * return random cards from the remaining cards according to the number given
     * @param num number of cards drew
     * @return required list of cards
     */
    public List<Card> drawCards(int num) {
        List<Card> ans = new ArrayList<>();

        while (num > 0) {
            checkRunOutOfCards();

            // return a random card
            int i = (int) Math.floor(Math.random() * cards.size());
            Card c = cards.get(i);
            ans.add(c);
            cards.remove(c);

            num--;
        }

        return ans;
    }

    public void addCard(Card c) {
        cards.add(c);
    }
}
