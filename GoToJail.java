/** Go to jail piece of the Monopoly board. Sets the player's inJail value
 *  to true and sends them to jail.
 *  @author Kevin Lowe
 */
public class GoToJail implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go to Jail";

    @Override
    public void effect(Player[] players, int turn) {
        Player jailed = players[turn];
        // jailed.inJail = true;
        // sendToJail(jailed);
    }

    @Override
    public String name() {
        return _name;
    }
}
