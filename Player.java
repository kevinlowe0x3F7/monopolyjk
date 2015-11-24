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
    /** Number of railroads owned */
    private int _railroads;
    /** True if the player is bankrupt*/
    private boolean _bankrupt;
    /** Jailed */
    private boolean _jailed;
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

    /** Returns number of railroads owned by player */
    public int railroads() {
        return _railroads;
    }

    /** Substracts the mnoney lost from the player's money */
    public void loseMoney(int moneyLost) {
        _money-= moneyLost;
    }  

    /** Adds the money gained to the player's money */
    public void gainMoney(int moneyGain) {
        _money+= moneyGain;
    }

    /** Returns if the player is bankrupt */
    public boolean bankrupt() {
        return _bankrupt;
    }

    /** Puts the player into jail */
    public void inJail(boolean jailed) {
        _jailed = jailed;
    }

    /** Moves the player a set amount of space */
    public void movePlayer(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.next();
        }
    }

    /** Buy unowned Property */
    public void buyProperty(BoardPiece proprety) {

    }

    /** Buy houses or hotels for properties */
    //public void upgradeProperty();

    /** Draws a Chance Card */
    public void drawChance() {

    }

    /** Draws a Community Chest Card */
    public void drawCommunityChest() {

    }
}
