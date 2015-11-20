/** Interface for a piece of the board in which the piece has some effect
 *  on the player.
 *  @author Kevin Lowe
 */
public interface BoardPiece {
    /** The effect given by this piece of the board on the PLAYER. */
    public void effect(Player player);

    /** Returns the name of this piece. */
    public String name();
}
