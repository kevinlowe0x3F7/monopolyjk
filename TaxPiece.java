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
    public boolean effect(Player current) {
        int money = 0;
        if (_name.equals("Luxury Tax")) {
            money = 100;
        } else {
            money = 200;
        }
        current.loseMoney(money);
        String line = "Player " + current.getID() + " loses $" + money +
            " from " + _name;
		if (current.game().gui() != null) {
		    current.game().gui().panel().status().addLine(line);
        }
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
