/** Go to jail piece of the Monopoly board. Sets the player's inJail value
 *  to true and sends them to jail.
 *  @author Kevin Lowe
 */
public class GoToJail implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Go to Jail";

    @Override
    public boolean effect(Player current) {
        current.inJail(true);
        current.jumpPlayer("Jail");
        String line = "Player " + current.getID() + " has been sent" +
            " to jail.";
        if (current.game().gui() != null) {
            current.game().gui().panel().status().addLine(line);
            current.game().nextPlayer();
            current.game().gui().panel().buttons().roll().setText("Roll Dice");
        }
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
