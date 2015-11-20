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
    /** The ID of the player that is currently on this BoardNode. If there
     *  is no player on this piece, the value is 0. */
    private int _id;

    /** Initializes this node with the given piece PIECE. _prev and
     *  _next are null. */
    public BoardNode(int piece) {
        this._piece = piece;
        this._prev = null;
        this._next = null;
        this._id = 0;
    }

    /** Initializes this node with the given piece PIECE and a
     *  PREV and NEXT. */
    public BoardNode(BoardNode prev, BoardPiece piece, BoardNode next) {
        this._piece = piece;
        this._prev = prev;
        this._next = next;
        this._id = 0;
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

    /** Returns the ID of the player on this node. */
    public int id() {
        return _id;
    }

    /** Sets the ID of this node, replacing it with NEWID. */
    public void setID(int newID) {
        _id = newID;
    }

    /** Set the previous Node of this BoardNode with PREV. */
    public void setPrevious(BoardNode prev) {
        this._prev = prev;
    }

    /** Set the next Node of this BoardNode with NEXT. */
    public void setNext(BoardNode next) {
        this._next = next;
    }

    /** Returns true if there is a player on this node, false otherwise. */
    public boolean hasPlayer() {
        return _id != 0;
    }
}
