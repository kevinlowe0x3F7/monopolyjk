/** The Go piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class GoPiece implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go";

    @Override
    public void effect(Player player) {
        return;
    }

    @Override
    public String name() {
        return _name;
    }
}
