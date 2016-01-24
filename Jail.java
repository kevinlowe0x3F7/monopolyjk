/** Jail piece of the Monopoly board.
 *  @author Kevin Lowe
 */
public class Jail implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Jail";

    @Override
    public boolean effect(Player current) {
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
