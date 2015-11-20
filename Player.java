import java.util.ArrayList;

public class Player {
    /** Properties owned by this Player */
    private ArrayList<BoardPiece> _properties;
    
    /** Location of the player */
    private BoardNode _location;

    /** ID of the player */
    private int _id;

    /** Money the player own */
    private int _money;
    
    /** Has "get out of jail free" */
    private boolean _jailFree;

    //Constructor Here
 




    /** Returns properties owned */
    public ArrayList<BoardPiece> properties() {
        return _properties;
    }
    
    /** Returns location of player */
    public BoardNode location() {
        return _location;
    }

    /** Returns players number ID */
    public int getID() {
        return _id;
    }

    /** Returns the amount of money the player has */
    public int money() {
        return _money;
    }

    /** Returns whether the player has a "get out of jail free" */
    public boolean jailFree() {
        return _jailFree;
    }
}
