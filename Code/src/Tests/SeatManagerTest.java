package Tests;

import Model.Card.EquipmentCard;
import Model.Card.EquipmentType;
import Model.Exception.InvalidInputException;
import Model.Player;
import Model.SeatManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by boscp on 2017-05-24.
 */
public class SeatManagerTest {

    @Before
    public void setup() {
        SeatManager.getInstance();
    }

    @Test (expected = InvalidInputException.class)
    public void testFindDistanceBetweenException() throws InvalidInputException{
        Player p = new Player("Peter");
        Player p1 = SeatManager.getInstance().getAlivePlayers().get(1);
        SeatManager.getInstance().findDistanceBetween(p,p1);
    }
    @Test
    public void testFindDistanceBetween() {
        Player p1 = SeatManager.getInstance().getAlivePlayers().get(1);
        Player p2 = SeatManager.getInstance().getAlivePlayers().get(4);
        try {
            assertEquals(3,SeatManager.getInstance().findDistanceBetween(p1,p2));
        } catch (InvalidInputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFindDistanceBetweenGoAround() {
        Player p1 = SeatManager.getInstance().getAlivePlayers().get(0);
        Player p2 = SeatManager.getInstance().getAlivePlayers().get(4);
        try {
            assertEquals(2,SeatManager.getInstance().findDistanceBetween(p1,p2));
        } catch (InvalidInputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAttackRange() {
        Player p = SeatManager.getInstance().getAlivePlayers().get(1);
        p.equip(new EquipmentCard("Arrow", 5, false, EquipmentType.WEAPON));
        try {
            assertEquals(5,SeatManager.getInstance().filterReachablePlayers(p).size());
        } catch (InvalidInputException e) {
            fail(e.getMessage());
        }
    }
}
