package Model.Card;

import Model.Player;

/**
 * Created by boscp on 2017-05-25.
 */
public class Kill extends BasicCard {

    /** HP drop each kill */
    public static final int HP_DROP = 1;

    public Kill() {
        super("Kill",true);
    }

    /**
     * execute a card
     */
    @Override
    public void execute(Player user, Player usee) {
        usee.beAttacked(HP_DROP);
    }

    /**
     * execute a card
     */
    @Override
    public void execute() {
        //TODO
    }
}
