/** Go to jail piece of the Monopoly board. Sets the player's inJail value
 *  to true and sends them to jail.
 *  @author Kevin Lowe
 */
public class GoToJail implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go to Jail";

    @Override
    public void effect(Player player) {
        return;
    }

    @Override
    public String name() {
        return _name;
    }
}
