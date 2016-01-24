/** A Community Chest piece on the board, indicating the player can pick
 *  up a card from the community chest pile.
 *  @author Kevin Lowe
 */
public class CommunityChestPiece implements BoardPiece {
    /** The name of this piece. */
    private final String _name = "Community Chest";

    @Override
    public boolean effect(Player current) {
        current.drawCommunityChest();
        return true;
    }

    @Override
    public String name() {
        return _name;
    }
}
