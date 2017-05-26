package Model;

import Model.Exception.InvalidInputException;

import java.util.*;

/**
 * Created by boscp on 2017-05-24.
 */
public class SeatManager implements Observer{

    /** The number of AI alivePlayers */
    public int NUM_AI_PLAYERS = 5;
    /** An instance of this manager */
    private static SeatManager instance;
    /** A list of alive alivePlayers in the game, seated in a round table */
    private List<Player> alivePlayers;
    /** A list of all Players in the game, including dead Players */
    private List<Player> allPlayers;

    /**
     * Construct a seating
     */
    private SeatManager() {
        alivePlayers = new ArrayList<>();
        allPlayers = new ArrayList<>();

        List<Player> AIPlayers = constructAIPlayers();
        alivePlayers.addAll(AIPlayers);
        alivePlayers.add(new Player("Me"));
        allPlayers.addAll(alivePlayers);
        
        instance = this;
    }

    public static SeatManager getInstance() {
        if (instance == null) new SeatManager();
        return instance;
    }

    /**
     * Find the minimum distance between two alivePlayers
     * @param p1 a player
     * @param p2 another player
     * @return minimum distance between two alivePlayers
     * @throws InvalidInputException when any of the two alivePlayers is not in this seating
     */
    public int findDistanceBetween(Player p1, Player p2) throws InvalidInputException {
        if (!alivePlayers.contains(p1) || !alivePlayers.contains(p2))
            throw new InvalidInputException("No such player");

        boolean metp1 = false;
        boolean metp2 = false;
        int dist = 0;

        Iterator<Player> it = alivePlayers.iterator();

        while (it.hasNext() && !metp1) {
            metp1 = it.next().equals(p1);
        }
        while (it.hasNext() && metp1 && !metp2) {
            metp2 = it.next().equals(p2);
            dist++;
        }

        if (!it.hasNext() && !metp2) {
            it = alivePlayers.iterator();
            while (it.hasNext() && metp1 && !metp2) {
                metp2 = it.next().equals(p2);
                dist++;
            }
        }

        return Math.min(dist, alivePlayers.size() - dist);
    }

    /**
     * return the next player of a given player in this seating
     * @param p the current player
     * @return the next player
     */
    public Player nextPlayer(Player p) {

        int i = alivePlayers.indexOf(p);
        if (i == alivePlayers.size() - 1) {
            return alivePlayers.get(0);
        } else {
            return alivePlayers.get(i + 1);
        }
    }

    public List<Player> filterReachablePlayers(Player player) throws InvalidInputException {
        int attack = player.getAttackRange();
        List<Player> ans = new ArrayList<>();

        for (Player p : alivePlayers) {
            if (!p.equals(player) && attack >= p.getEscapeRange() + findDistanceBetween(player, p))
                ans.add(p);
        }
        return ans;
    }

    /**
     * Construct a list of AIPlayers according to the number indicated;
     * @return a list of AIPlayers
     */
    private List<Player> constructAIPlayers() {
        List<Player> AIPlayers = new ArrayList<>();

        for (int i = 1; i <= NUM_AI_PLAYERS; i++) {
            AIPlayers.add(new AIPlayer("" + i));
        }

        return AIPlayers;
    }

    /**
     * get the first player in the seating
     * @return the first player
     */
    public Player getFirstPlayer() {
        return alivePlayers.get(0);
    }

    public List<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Player.class) {
            Player p = (Player) arg;
            if (!p.getAlive())
                alivePlayers.remove(p);
        }
    }
}
