import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/** Tests of Monopoly
 *  @author Kevin Lowe
 */

public class MonopolyTests {

	@Test
	public void testBoardNode() {
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MonopolyTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
