package Model.Card;

/**
 * Created by boscp on 2017-05-26.
 */
public class EquipmentCard extends AbstractCard {

    private EquipmentType type;
    /** effect on attack range of a weapon (SHIELD and ARMOR are 0) */
    private int range;
    /** true if passive, false if offensive */
    private boolean passive;

    public EquipmentCard(String name, int range, boolean passive, EquipmentType type) {
        super(name, true);
        this.range = range;
        this.passive = passive;
        this.type = type;
    }

    public int getRange() {return range;}

    public boolean getPassive() {return passive;}

    public EquipmentType getEquipmentType() {return type;}

    @Override
    public void execute() {
        owner.equip(this);
    }
}
