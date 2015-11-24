import java.util.ArrayList;

public class Player {
    /** Name of the Player */
    private String _name;
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
    /** Number of Utilities owned */
    private int _utilities;
    /** True if the player is bankrupt */
    private boolean _bankrupt;
    /** Jailed */
    private boolean _jailed;
    /** Number rolled */
    private int _lastRoll;
    


    public Player(int id, int money, BoardNode location) {
        _id = id;
        _money = money;
        _location = location;
    }

    /** Returns the name of the player */
    public String getName() {
        return _name;
    }

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


    /** Returns the number of Utilities owned by player */
    public int utilities() {
        return _utilities;
    }


    /** Substracts the mnoney lost from the player's money */
    public void loseMoney(int moneyLost) {
        _money-= moneyLost;
        //Deal with mortage and bankrupty
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

    /** Jumps the player to set BoardPiece */
    public void jumpPlayer(BoardPiece jumpLocation) {
        _location = jumpLocation;
    }

    /** Traverses the player to a set BoardPiece */
    public void traversePlayer(BoardPiece traverseLocation) {
        for (BoardPiece curr = _location; curr != traverseLocation; curr = curr.next()) {
            _location = curr.next();
        }
    }

    /** Moves the player a set amount of space */
    public void movePlayer(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.next();
        }
    }


    /** (Joseph) Buy unowned Property */
    public void buyProperty(Property property) {
        loseMoney(property.price());
        _properties.add(property);
        property.setOwner(this);
        if (property.name().equals("Pennsylvania Railroad") || property.name().equals("Short Line") 
        || property.name().equals("Reading Railroad") || property.name().equals("PB. & O. Railroad")) {
            _railroads +=1;
        }
        // } else if (property.name().equals("Water Works") || property.name().equals("Electric Company")) {
        //     _utilities +=1;
        // }
    }


    /** Returns the last roll the player rolled */
    public int getLastRoll() {
        return _lastRoll;
    }

    public void setLastRoll(int lastRoll) {
        _lastRoll = lastRoll;
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
