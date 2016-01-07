import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;

public class Player {
    /** Name of the Player */
    private String _name;
    /** Properties owned by this Player, mapped from group to property. */
    private HashMap<String, HashSet<Property>> _properties;
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
    /** Numbers rolled */
    private int[] _rolls;
    /** My random generator. */
    private Random _source;
    /** Monopoly game that contains the player */
    private Monopoly _monopoly;

    
    // TODO chance drawing, community chest drawing, general move method,
    // UNIT TEST

    public Player(int id, int money, BoardNode location, Monopoly monopoly) {
        _id = id;
        _money = money;
        _location = location;
        _monopoly = monopoly;
        _properties = new HashMap<String, HashSet<Property>>();
        _rolls = new int[2];
        _source = new Random();
    }


//============================ Getters ==================================

    /** Returns the name of the player */
    public String getName() {
        return _name;
    }

    /** Returns properties owned */
    public HashMap<String, HashSet<Property>> properties() {
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
    
    /** Returns if the player is bankrupt */
    public boolean bankrupt() {
        return _bankrupt;
    }    

    /** Returns the rolls of the player */
    public int[] rolls() {
        return _rolls;
    }

//=========================== Setters ================================
    
    /** Substracts the money lost from the player's money */
    public void loseMoney(int moneyLost) {
        _money -= moneyLost;
        //Deal with mortage and bankrupty
    }  

    /** Adds the money gained to the player's money */
    public void gainMoney(int moneyGain) {
        _money += moneyGain;
    }

    /** Puts the player into jail */
    public void inJail(boolean jailed) {
        _jailed = jailed;
    }

    /** Sets the JailFree as false or true */
    public void jailFree(boolean condition) {
        _jailFree = condition;
    }

//========================== Actions ==================================

    /** Takes care of players turn */
    public void turn() {
        // Check if the player is in Jail
    }

    /** Rolls a die, returning an integer between 1 and 6. */
    public int rollDice() {
        return (_source.nextInt(6) + 1);
    }


    /** Jumps the player to Jail. Does not collect $200 from passing go */
    public void jumpPlayer(String jumpLocation) {
        while (!_location.piece().name().equals(jumpLocation)) {
            _location = _location.next();
        }
    }

    /** Traverses the player to a set BoardPiece. If the player passes go collect $200 */
    public void traversePlayer(String traverseLocation) {
        while (!_location.piece().name().equals(traverseLocation)) {
            _location = _location.next();
            if (_location.piece().name().equals("Go")) {
                _location.piece().effect(this);
            }
        }
        _location.piece().effect(this);
    }


    /** Moves the player a set amount of space depending on the dice roll */
    public void movePlayer(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.next();
        }
        //Action/effect of landed piece
        if (_location.piece() instanceof Property) {
            Property property = (Property) _location.piece();
            if (property.isOwned()) {
                property.effect(this);
            } else {
                buyProperty(property);
            }
        } else {
            _location.piece().effect(this);
        }
    }
    
    /** Moves the player backwards for a set number of spaces */
    public void backstep(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.prev();
        }
        _location.piece().effect(this);
    }

    /** Handles the special traversals that moves the player
     *  to the nearest railroad/utilies and has a special charge */
    public void specialTraversePlayer(String traverseLocation) {
        if (traverseLocation.equals("railroad")) {
            while (!(_location.piece() instanceof Railroad)) {
                _location = _location.next();
            }
            //Special Effect of the chance card
            Railroad landedPiece = (Railroad) _location.piece();
            landedPiece.specialEffect(this);
        } else {
            while (!(_location.piece() instanceof Utility)) {
                _location = _location.next();
            }
            //Special Effect of the the chance card
            Utility landedPiece = (Utility) _location.piece();
            landedPiece.specialEffect(this, rollDice(), rollDice());
        }
    }

    /** Charges the player for the houses and hotel in possession */
    public void propertyMaintenance(int houseCost, int hotelCost) {
        //Code here
        int maintenance = 0;
        for (String group: _properties.keySet()) {
            for (Property prop: _properties.get(group)) {
                if (!prop.isFull() || !(prop instanceof Street)) {
                    return;
                } else {
                    Street st = (Street) prop;
                    int totalHouses = st.getHouses();
                    // Hotel Case
                    if (totalHouses > 4) {
                        maintenance += hotelCost;
                        totalHouses--;
                    }
                    for (int i = totalHouses; i > 0; i--) {
                        maintenance += houseCost;
                    }
                }
            }
        }
        loseMoney(maintenance);
    }


    /** (Joseph) Buy unowned Property and adds it into the list of properties owned by the Player*/
    public void buyProperty(Property property) {
        // TODO does this assume that the property can be bought?
        // may need check whether person has the money to buy
        loseMoney(property.price());
        String group = property.getGroup();
        if (!_properties.containsKey(group)) {
            _properties.put(group, new HashSet<Property>());
        }
        _properties.get(group).add(property);
        property.setOwner(this);
        Property.checkFull(this, property);
        if (property.getGroup().equals("railroad")) {
            _railroads +=1;
        }
    }

    /** Buy houses or hotels for properties. HOUSES indicates the number
     *  of houses that the player wants to buy. Returns true if
     *  successfully upgraded. */
    public boolean upgradeProperty(Property upgrading, int houses) {
        HashSet<Property> properties = _properties.get(upgrading);
        if (properties == null) {
            return false;
        } else if (!properties.contains(upgrading)) {
            return false;
        } else if (!upgrading.isFull()) {
            return false;
        } else if (!(upgrading instanceof Street)) {
            return false;
        } else {
            Street street = (Street) upgrading;
            if (street.getHouses() + houses > 5 ||
                    street.getHouses() + houses < 0) {
                return false;
            }
            street.setHouses(street.getHouses() + houses);
            return true;
        }
    }

    /** Draws a Chance Card */
    public void drawChance() {
        // TODO switch control of drawing chance to Monopoly so that it
        // can monitor when to reset the deck and stuff, (Kevin) can take
        // care of it
        _monopoly.chance()[_monopoly.chanceIndex()].effect(this);
    }

    /** Draws a Community Chest Card */
    public void drawCommunityChest() {
        // TODO switch control of drawing chest to Monopoly so that it
        // can monitor when to reset the deck and stuff, (Kevin) can take
        // care of it
        _monopoly.chest()[_monopoly.chestIndex()].effect(this);
    }
}
