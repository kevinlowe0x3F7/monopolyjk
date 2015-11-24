/** Go to jail piece of the Monopoly board. Sets the player's inJail value
 *  to true and sends them to jail.
 *  @author Kevin Lowe
 */
public class GoToJail implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go to Jail";

    @Override
    public void effect(Player current) {
        // Player jailed = players[turn];
        current.inJail(true); //Keep all instance variables private;

        // jailed.inJail = true;
        // sendToJail(jailed);
    }

    @Override
    public String name() {
        return _name;
    }
}
