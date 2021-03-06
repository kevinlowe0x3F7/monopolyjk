/** Chance piece on the Monopoly board, indicating the player that lands
 *  on it can pick up a chance card from the pile.
 *  @author Kevin Lowe
 */
public class ChancePiece implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Chance";

    @Override
    public boolean effect(Player current) {
        current.drawChance();
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
