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
        assertEquals(1450, one.money());
        assertEquals("Baltic Avenue", one.location().piece.name());
        assertTrue(one.properties().containsKey("brown"));
    }

    @Test
    public void testIncomeTax() {
        Monopoly m = new Monopoly(2);
        Player one = m.players()[1];
        one.movePlayer(4);
        assertEquals(1300, one.money());
        assertEquals("Income Tax", one.location().piece.name());
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
                continue;
            } else if (nextLine.indexOf("sun.reflect.") != -1) {
                continue;
            } else {
                newStack += (nextLine + '\n');
            }
        }
        input.close();
        return newStack.substring(0, newStack.lastIndexOf('\n'));
    }
}
