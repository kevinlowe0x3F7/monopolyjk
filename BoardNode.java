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

    /** Initializes this node with the given piece PIECE. _prev and
     *  _next are null. */
    public BoardNode(BoardPiece piece) {
        this._piece = piece;
        this._prev = null;
        this._next = null;
    }

    /** Initializes this node with the given piece PIECE and a
     *  PREV and NEXT. Assumes that prev and next are not null. */
    public BoardNode(BoardNode prev, BoardPiece piece, BoardNode next) {
        this._piece = piece;
        this._prev = prev;
        this._next = next;
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
}

