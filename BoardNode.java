/** A node representing one piece of the board, containing pointers to
 *  the pieces to the left and right of me.
 *  @author Kevin Lowe
 */
public class BoardNode {
    /** The piece on this node. */
    private final BoardPiece _piece;
    /** My previous node. */
    private BoardNode _prev;
    /** My next node. */
    private BoardNode _next;
    /** The IDs that are on this node, listed as a boolean array, with
     *  true indicating that the player is on this node. */
    private boolean[] _ids;

    /** Initializes this node with the given piece PIECE. _prev and
     *  _next are null. */
    public BoardNode(BoardPiece piece) {
        this._piece = piece;
        this._prev = null;
        this._next = null;
        this._ids = new boolean[8];
    }

    /** Initializes this node with the given piece PIECE and a
     *  PREV and NEXT. Assumes that prev and next are not null. */
    public BoardNode(BoardNode prev, BoardPiece piece, BoardNode next) {
        this._piece = piece;
        this._prev = prev;
        this._next = next;
        this._ids = new boolean[8];
        prev._next = this;
        next._prev = this;
    }

    /** Returns the piece on this node. */
    public BoardPiece piece() {
        return _piece;
    }

    /** Returns my previous node. */
    public BoardNode prev() {
        return _prev;
    }

    /** Returns my next node. */
    public BoardNode next() {
        return _next;
    }

    /** Returns true if PLAYER is on this node. */
    public boolean playerHere(Player player) {
        return _ids[player.getID()];
    }

    /** Adds PLAYER to this node. */
    public void addPlayer(Player player) {
        _ids[player.getID()] = true;
    }

    /** Removes PLAYER from this node. */
    public void removePlayer(Player player) {
        _ids[player.getID()] = false;
    }

    /** Set the previous Node of this BoardNode with PREV. */
    public void setPrev(BoardNode prev) {
        this._prev = prev;
        prev._next = this;
    }

    /** Set the next Node of this BoardNode with NEXT. */
    public void setNext(BoardNode next) {
        this._next = next;
        next._prev = this;
    }

    /** Returns true if there is a player on this node, false otherwise. */
    // (Joseph) changed to accomadate 8 players
    public boolean hasPlayer() {
        return _ids[1] || _ids[2] || _ids[3] || _ids[4] || _ids[5] ||_ids[6] ||_ids[7] ||_ids[8];
    }
}

