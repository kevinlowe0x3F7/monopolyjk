/** Tax piece on the Monopoly board, can be either Luxury or Income.
 *  @author Kevin Lowe
 */
public class TaxPiece implements BoardPiece {
    /** The name of this piece. */
    private final String _name;

    /** Sets the name of this TaxPiece to the given NAME, which will
     *  determine its tax amount depending on the name. */
    public TaxPiece(String name) {
        this._name = name;
    }

    @Override
    public void effect(Player current) {
        if (_name.equals("Luxury Tax")) {
            current.loseMoney(100);
        } else {
            current.loseMoney(200);
        }
    }

    @Override
    public String name() {
        return _name;
    }
}