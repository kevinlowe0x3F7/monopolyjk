import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Scanner;

/** Tests of PlayerTests
 *  @author Kevin Lowe
 */

public class PlayerTests {
    
    @Test
    public void testInstantiation() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        assertEquals(1500, one.money());
        assertEquals(1, one.getID());
        assertEquals("Go", one.location().piece().name());
    }

    @Test
    public void testBasicMovementandBuyProperty() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        one.movePlayer(3);
        assertEquals(1440, one.money());
        assertEquals("Baltic Avenue", one.location().piece().name());
        assertTrue(one.properties().containsKey("brown"));
    }

    @Test
    public void testIncomeTax() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        one.movePlayer(4);
        assertEquals(1300, one.money());
        assertEquals("Income Tax", one.location().piece().name());
    }

    @Test
    public void testRentPayment() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        Player two = m.players()[2];
        one.movePlayer(3);
        two.movePlayer(3);
        assertEquals(1496, two.money());
        assertEquals("Baltic Avenue", two.location().piece().name());
        assertEquals(1444, one.money());

        two.backstep(3);
        Property baltic = (Property) one.location().piece();
        assertFalse(one.upgradeProperty(baltic, 5));
        one.buyProperty((Property) two.location().next().piece());
        assertEquals(1384, one.money());
        assertTrue(one.upgradeProperty(baltic, 5));
        assertEquals(1134, one.money());
        two.movePlayer(3);
        assertEquals(1584, one.money());
    }

    @Test
    public void testSimpleTraversal() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        one.traversePlayer("Boardwalk");
        assertEquals(1100, one.money());
        assertEquals("Boardwalk", one.location().piece().name());
        assertTrue(one.properties().containsKey("blue"));
        one.traversePlayer("Park Place");
        assertEquals(950, one.money());
    }
        
    @Test
    public void testSpecialTraversal() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        Player two = m.players()[2];
        one.movePlayer(5);
        one.movePlayer(7);
        one.movePlayer(3);
        one.movePlayer(10);
        one.movePlayer(10);
        assertEquals(4, one.railroads()); 
        two.specialTraversePlayer("railroad");
        assertEquals(1100, two.money());
        assertEquals("Reading Railroad", two.location().piece().name());
        two.specialTraversePlayer("utility");
        assertTrue(two.money() < 1100);
        assertTrue(two.money() >= 980);
        assertEquals("Electric Company", two.location().piece().name());
    }

    @Test
    public void testJail() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        Player two = m.players()[2];
        one.movePlayer(30);
        assertTrue(one.isJailed());
        assertEquals("Jail", one.location().piece().name());
        two.movePlayer(10);
        assertFalse(two.isJailed());
        assertEquals("Jail", two.location().piece().name());
    }

    @Test
    public void testRandomSource() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        for (int i = 0; i < 50; i++) {
            int result = one.rollDice();
            assertTrue(result <= 6);
            assertTrue(result >= 1);
        }
    }

    @Test
    public void testPropertyMaintenanceChanceCard() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        one.traversePlayer("Park Place");
        Property park = (Property) one.location().piece();
        one.traversePlayer("Boardwalk");
        Property boardwalk = (Property) one.location().piece();
        assertEquals(750, one.money());
        assertTrue(((Property) one.location().piece()).isFull());
        one.gainMoney(3000);
        assertTrue(one.upgradeProperty(park, 4));
        assertFalse(one.upgradeProperty(park, 4));
        assertTrue(one.upgradeProperty(boardwalk, 5));
        assertEquals(1950, one.money());
        one.propertyMaintenance(40, 115);
        assertEquals(1675, one.money());
    }

    @Test
    public void testMortgaging() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        Player two = m.players()[2];
        one.movePlayer(1);
        one.movePlayer(2);
        assertEquals(1380, one.money());
        one.mortgageProperty((Property) one.location().piece());
        assertEquals(1410, one.money());
        two.movePlayer(3);
        assertEquals(1500, two.money());
    
        one.movePlayer(2);
        assertEquals(1210, one.money());
        one.mortgageProperty((Property) one.location().piece());
        assertEquals(1310, one.money());
        two.movePlayer(2);
        assertEquals(1500, two.money());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PlayerTests.class);
        int tests = result.getRunCount();
        if (result.wasSuccessful()) {
            System.out.printf("%d tests. All passed.%n", tests);
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
                System.out.println(cutStack(failure.getTrace()));
            }
            int passed = tests - result.getFailureCount();
            System.out.printf("%d tests. %d passed.%n", tests, passed);
        }
    }

    /** Helper method to shorten stack trace. */
    public static String cutStack(String fullStack) {
        Scanner input = new Scanner(fullStack);
        String newStack = "";
        while (input.hasNext()) {
            String nextLine = input.nextLine();
            if (nextLine.indexOf("org.junit.") != -1) {
                continue;
            } else if (nextLine.indexOf("java.lang.") != -1) {
                if (nextLine.indexOf("Exception") == -1) {
                    continue;
                }
            } else if (nextLine.indexOf("sun.reflect.") != -1) {
                continue;
            }
            newStack += (nextLine + '\n');
        }
        input.close();
        return newStack.substring(0, newStack.lastIndexOf('\n'));
    }
}
