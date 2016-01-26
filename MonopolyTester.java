import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Scanner;

/** Tests of ___
 *  @author Kevin Lowe
 */

public class MonopolyTester {



    @Test
    public void testSimple() {
        int tester = 5;
        assertEquals(tester, 5);
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MonopolyTester.class);
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
