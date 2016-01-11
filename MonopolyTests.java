import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Scanner;

/** Tests of Monopoly
 *  @author Kevin Lowe
 */

public class MonopolyTests {

	@Test
	public void testBoardNodeandMiscBoardPieces() {
	    int x = 1;
	    BoardNode go = new BoardNode(new GoPiece());
	    assertEquals("16", "Go", go.piece().name());
	    BoardNode chest = new BoardNode(new CommunityChestPiece());
	    go.setPrev(chest);
	    BoardNode goToJail = new BoardNode(new GoToJail());
	    go.setNext(goToJail);
	    assertEquals("21", "Go to Jail", go.next().piece().name());
	    assertEquals("22", "Community Chest", go.prev().piece().name());
	    assertEquals("23", "Go", go.next().prev().piece().name());

	    BoardNode freeParking = new BoardNode(new FreeParking());
	    BoardNode jail = new BoardNode(goToJail,
	            new Jail(), freeParking);
	    BoardNode luxury = new BoardNode(new TaxPiece("Luxury Tax"));
	    freeParking.setNext(luxury);
	    BoardNode chance = new BoardNode(new ChancePiece());
	    chest.setPrev(chance);
	    BoardNode income = new BoardNode(luxury,
	            new TaxPiece("Income Tax"), chance);
	    BoardNode curr = go;
	    
	    assertEquals("36", "Go", curr.piece().name());
	    curr = curr.next().next();
	    assertEquals("38", "Jail", curr.piece().name());
	    curr = curr.next().next();
	    assertEquals("40", "Luxury Tax", curr.piece().name());
	    curr = curr.next().next();
	    assertEquals("42", "Chance", curr.piece().name());
	    curr = curr.next().next();
	    assertEquals("44", "Go", curr.piece().name());
	    curr = curr.prev();
	    assertEquals("46", "Community Chest", curr.piece().name());
	    curr = curr.prev().prev();
	    assertEquals("48", "Income Tax", curr.piece().name());
	    curr = curr.prev().prev();
	    assertEquals("50", "Free Parking", curr.piece().name());
	    curr = curr.prev().prev();
	    assertEquals("52", "Go to Jail", curr.piece().name());
	    curr = curr.prev();
	    assertEquals("54", "Go", curr.piece().name());
    }

    @Test
    public void testBoard() {
        Monopoly m = new Monopoly(2);
        BoardNode test = m.getBoard();
        for (int i = 0; i < 42; i++) {
            test = test.next();
        }
    }

    @Test
    public void testChanceDeck() {
        Monopoly m = new Monopoly(2);
        Chance[] deck = m.chance();
        Chance prev = deck[0];
        m.drawChance();
        for (int i = 1; i < (deck.length * 2); i++) {
            Chance next = m.drawChance();
            assertFalse(prev.name().equals(next.name()));
            prev = next;
        }

        Monopoly m1 = new Monopoly(2);
        Chance[] deck1 = m1.chance();
        boolean same = deck[0].name().equals(deck1[0].name()) &&
            deck[1].name().equals(deck1[1].name()) &&
            deck[2].name().equals(deck1[2].name()) &&
            deck[3].name().equals(deck1[3].name()) &&
            deck[4].name().equals(deck1[4].name());
        assertFalse(same);
    }

    @Test
    public void testCommunityChestDeck() {
        Monopoly m = new Monopoly(2);
        CommunityChest[] deck = m.chest();
        CommunityChest prev = deck[0];
        m.drawChest();
        for (int i = 1; i < (deck.length * 2); i++) {
            CommunityChest next = m.drawChest();
            assertFalse(prev.name().equals(next.name()));
            prev = next;
        }

        Monopoly m1 = new Monopoly(2);
        CommunityChest[] deck1 = m1.chest();
        boolean same = deck[0].name().equals(deck1[0].name()) &&
            deck[1].name().equals(deck1[1].name()) &&
            deck[2].name().equals(deck1[2].name()) &&
            deck[3].name().equals(deck1[3].name()) &&
            deck[4].name().equals(deck1[4].name());
        assertFalse(same);
    }

    @Test
    public void testPlayerIndexing() {
        Monopoly two = new Monopoly(2);
        Player curr = two.current();
        assertEquals(1, curr.getID());
        two.nextPlayer();
        curr = two.current();
        assertEquals(2, curr.getID());
        two.nextPlayer();
        curr = two.current();
        assertEquals(1, curr.getID());

        Monopoly three = new Monopoly(3);
        three.players()[2] = null;
        curr = three.current();
        assertEquals(1, curr.getID());
        three.nextPlayer();
        curr = three.current();
        assertEquals(3, curr.getID());
        three.nextPlayer();
        curr = three.current();
        assertEquals(1, curr.getID());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MonopolyTests.class);
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
