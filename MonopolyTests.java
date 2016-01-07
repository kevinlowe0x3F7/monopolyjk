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
