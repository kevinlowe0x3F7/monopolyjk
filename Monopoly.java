/** Class that implements the game (Model) for Monopoly.
 *  @author Kevin Lowe
 *  @version 1.0 11/19/2015
 */
public class Monopoly {
    /** The board for this game, represented as a circular doubly
     *  linked list. */
    private BoardNode _board;

    /** The deck of Community Chest cards. Commented out until
     *  implementation of CommunityChest objects are complete. */
    // private CommunityChest[] _chest;

    /** The deck of Chance cards. Commented out until implementation
     *  of Chance objects are complete. */
    // private Chance[] _chance;

    /** The players of this game. */
    private Player[] _players;

    /** The number of players in this game. */
    private int _numPlayers;
}
