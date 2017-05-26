package Model.Card;

import Model.Player;

/**
 * Created by boscp on 2017-05-24.
 */
public abstract class AbstractCard implements Card{

    protected String name;
    protected boolean offense;
    protected Player owner;

    public AbstractCard(String name, boolean offense) {
        this.name = name;
        this.offense = offense;
    }

    @Override
    public void setOwner(Player owner) {this.owner = owner;}

    public String getName() {
        return name;
    }

    public boolean getOffense() {
        return offense;
    }
}
