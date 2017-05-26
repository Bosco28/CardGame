package Model.Card;

import Model.Player;

/**
 * Created by boscp on 2017-05-25.
 */
public interface Card {

    /**
     * execute a card
     */
    void execute();

    /**
     * get the name of a card
     */
    String getName();

    /**
     * set the owner of a card
     */
    void setOwner(Player p);
}
