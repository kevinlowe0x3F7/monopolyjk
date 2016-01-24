/** The Free Parking piece on the board.
 *  @author Kevin Lowe
 */
public class FreeParking implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Free Parking";
    
    @Override
    public boolean effect(Player current) {
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
