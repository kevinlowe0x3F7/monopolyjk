/** The Go piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class GoPiece implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go";

    @Override
    public boolean effect(Player current) {
        current.gainMoney(200);
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
