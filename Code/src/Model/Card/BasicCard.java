package Model.Card;

import Model.Player;

/**
 * Created by boscp on 2017-05-25.
 */
public abstract class BasicCard extends AbstractCard{

    public static final String KILL = "KILL";
    public static final String DODGE = "DODGE";
    public static final String FRUIT = "FRUIT";

    public BasicCard(String name, boolean offense) {
        super(name,offense);
    }

    public void execute(Player user, Player usee) {}
}
