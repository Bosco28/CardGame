package Model;

import Model.Card.Card;
import Model.Card.EquipmentCard;
import Model.Card.EquipmentType;

import java.util.*;

/**
 * Created by boscp on 2017-05-24.
 */
public class Player {

    /** default hp */
    private static final int HEALTH = 4;

    /** A list of cards that this player has */
    private Map<Integer, Card> cards;
    /** Name of this player */
    private String name;
    /** Hit point of this player (player is out when HP == 0)*/
    private int hp;
    /** An array of the equipment of this player */
    private Map<EquipmentType,EquipmentCard> equipments;
    /** Status of a player */
    private boolean alive;

    /**
     * Construct a new player with HEALTH hp
     * @param name name
     */
    public Player(String name) {
        this.name = name;
        hp = HEALTH;
        cards = new HashMap<>();
        alive = true;
        equipments = new HashMap<>(3);
        equipments.put(EquipmentType.WEAPON, null);
        equipments.put(EquipmentType.ARMOR, null);
        equipments.put(EquipmentType.HORSE, null);
    }

    /**
     * draw a certain number of cards from card Manager and add it to cards
     * @param num
     */
    public void drawCards(int num) {
        List<Card> newCards = CardManager.getInstance().drawCards(num);
        int start = cards.size();

        for (Card c : newCards) {
            start++;
            c.setOwner(this);
            cards.put(start,c);
        }
    }

    /**
     * use the card specified by index i
     * @param i index of the card to be used
     */
    public void useACard(int i) {
        Card c = cards.get(i);
        c.execute();
        c.setOwner(null);
        cards.remove(i);
        reorder();
    }

    /**
     * discard the card specified by index i
     * @param i index of the card to be discard
     */
    public void discardACard(int i) {
        cards.get(i).setOwner(null);
        cards.remove(i);
        reorder();
    }

    /**
     * reorder the index of cards in the map
     */
    public void reorder() {
        Collection<Card> allCards = new ArrayList<>();
        allCards.addAll(cards.values());
        cards.clear();
        int i = 0;

        for (Card c : allCards) {
            i++;
            cards.put(i,c);
        }
    }

    /**
     * subtract a given number of hp
     * @param i number of hp to be subtracted
     */
    public void beAttacked(int i) {
        hp = hp - i;
        if (hp <= 0)
            alive = false;
    }

    /**
     * equipment an equipment
     * @param c the equipment card that the player want to equip
     */
    public void equip(EquipmentCard c) {
        equipments.put(c.getEquipmentType(),c);
    }

    /**
     * get the Attack range of this player
     * @return the attack range of this player
     */
    public int getAttackRange() {
        int ans = 1;
        EquipmentCard weapon = equipments.get(EquipmentType.WEAPON);
        EquipmentCard horse = equipments.get(EquipmentType.HORSE);

        if (weapon != null)
            ans = weapon.getRange();
        if (horse != null && !horse.getPassive())
            ans += horse.getRange();

        return ans;
    }

    /**
     * get the escape range of this player
     * @return the escape range of this player
     */
    public int getEscapeRange() {
        int ans = 0;
        EquipmentCard horse = equipments.get(EquipmentType.HORSE);

        if (horse != null && horse.getPassive())
            ans += horse.getRange();

        return ans;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Collection<Card> getCards() {
        return cards.values();
    }

    public boolean getAlive() {
        return alive;
    }

    public Map<Integer, Card> getIndexAndCards() {
        return cards;
    }
}
