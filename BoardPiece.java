/** Interface for a piece of the board in which the piece has some effect
 *  if a player lands on it.
 *  @author Kevin Lowe
 */
public interface BoardPiece {
    /** The effect given by this piece of the board when a player lands
     *  on it.
     *  @param players the list of players
     *  @param turn the index of the current player
     */

    public boolean effect(Player player);

    /** Returns the name of this piece. */
    public String name();
}
