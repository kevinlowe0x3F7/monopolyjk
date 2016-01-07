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
        one.buyProperty((Property) two.location().next().piece());
        assertEquals(1384, one.money());
        assertTrue(one.upgradeProperty(baltic, 5));
        assertEquals(1134, one.money());
        two.movePlayer(3);
        assertEquals(1584, one.money());
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
