package Model;

/**
 * Created by boscp on 2017-05-24.
 */
public class GameSystem {

    public static final int NUM_OF_CARDS_AT_BEGINNING_OF_GAME = 4;
    public static final int NUM_OF_CARDS_AT_BEGINNING_OF_ROUND = 2;
    public static final String DISCARD = "DISCARD";

    /** the current player of the round */
    private Player current;
    /** round # of this round */
    private int roundNum;

    public GameSystem() {
        current = SeatManager.getInstance().getFirstPlayer();
        startGame();
        roundNum = 1;
        startRound();
    }

    public void startGame() {
        for (Player p : SeatManager.getInstance().getAlivePlayers()) {
            p.drawCards(NUM_OF_CARDS_AT_BEGINNING_OF_GAME);
        }
    }
    /**
     * end a round
     */
    public boolean endRound()  {
        if (current.getCards().size() > current.getHp())
            return false;
        else {
            nextRound();
            return true;
        }
    }

    /**
     * start a round, let the current player draw two cards
     */
    public void startRound() {
        current.drawCards(NUM_OF_CARDS_AT_BEGINNING_OF_ROUND);
    }

    /**
     * proceed to the next round
     */
    public void nextRound() {
        current = SeatManager.getInstance().nextPlayer(current);
        roundNum++;
        startRound();
    }

    public int getRoundNum() {
        return roundNum;
    }

    public Player getCurrent() {
        return current;
    }
}
