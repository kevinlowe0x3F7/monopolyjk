import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
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
    /** Jailed */
    private boolean _jailed;
    /** Number of turn in jail */
    private int _jailedTurns;
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
    
    /** Returns the rolls of the player */
    public int[] rolls() {
        return _rolls;
    }

    /** Returns true if this player is jailed, false otherwise. */
    public boolean isJailed() {
        return _jailed;
    }

    /** Returns the last rolls of the player. */
    public int getLastRoll() {
        return _rolls[0] + _rolls[1];
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
        _jailedTurns = 3;
    }

    /** Sets the JailFree as false or true */
    public void jailFree(boolean condition) {
        _jailFree = condition;
    }

//========================== Actions ==================================

    /** Takes care of players turn */
    public void turn() {
        // Jailed Turn
        if (_jailed) {
            jailedTurn();
        // Normal Turn
        } else {
            boolean playerTurn = true;
            int numRolls = 0;
            while (playerTurn) {
                _rolls[0] = rollDice(); _rolls[1] = rollDice();
                if (_rolls[0] != _rolls[1]) {
                    playerTurn = false;
                } else {
                    numRolls++;
                }
                // If 3 doubles rolled -> Jail
                if (numRolls == 3) {
                    inJail(true);
                    jumpPlayer("Jail");
                    return;  
                }
                movePlayer(_rolls[0] + _rolls[1]);
            }
        }
    }

    private void jailedTurn() {
        if (_jailedTurns > 0) {
            // Case 1: Pay $50 fine before //TODO FE
            // Case 2: Get out of Jail Free //TODO FE
            // Case 3: Roll Doubles
            _rolls[0] = rollDice(); _rolls[1] = rollDice();
            if (_rolls[0] == _rolls[1]) {
                _jailedTurns = 0;
                _jailed = false;
                movePlayer(_rolls[0] + _rolls[1]);
            // Case 4: Stay in Jail
            } else {
                _jailedTurns--;
                if (_jailedTurns == 0) {
                    //Force Removal from jail
                    loseMoney(50);
                    _jailed = false;
                    movePlayer(_rolls[0] + _rolls[1]);
                }
            }
        }  
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

    /** Traverses the player to a set BoardPiece. If the player
     *  passes go collect $200. (Kevin) Added clause to check if it
     *  is a property and whether it is buyable or not. */
    public void traversePlayer(String traverseLocation) {
        while (!_location.piece().name().equals(traverseLocation)) {
            _location = _location.next();
            if (_location.piece().name().equals("Go")) {
                _location.piece().effect(this);
            }
        }
        resolveLanding();
    }


    /** Moves the player a set amount of space depending on the dice roll */
    public void movePlayer(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.next();
            if (_location.piece().name().equals("Go")) {
                _location.piece().effect(this);
            }
        }
        resolveLanding();
    }
    
    /** Moves the player backwards for a set number of spaces */
    public void backstep(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            _location = _location.prev();
        }
        _location.piece().effect(this);
    }

    /** Handles the special traversals that moves the player
     *  to the nearest railroad/utilies and has a special charge.
     *  If the property is unowned, however, the player may buy it. */
    public void specialTraversePlayer(String traverseLocation) {
        if (traverseLocation.equals("railroad")) {
            while (!(_location.piece() instanceof Railroad)) {
                _location = _location.next();
                if (_location.piece().name().equals("Go")) {
                    _location.piece().effect(this);
                }
            }
            Railroad landedPiece = (Railroad) _location.piece();
            // Changed by Kevin to accomodate unowned property
            if (landedPiece.isOwned()) {
                landedPiece.specialEffect(this);
            } else {
                buyProperty(landedPiece);
            }
        } else {
            while (!(_location.piece() instanceof Utility)) {
                _location = _location.next();
                if (_location.piece().name().equals("Go")) {
                    _location.piece().effect(this);
                }
            }
            Utility landedPiece = (Utility) _location.piece();
            // Changed by Kevin to accomodate unowned property
            if (landedPiece.isOwned()) {
                landedPiece.specialEffect(this, rollDice(), rollDice());
            } else {
                buyProperty(landedPiece);
            }
        }
    }

    /** Charges the player for the houses and hotel in possession.
     *  (Kevin) fixing code, the player doesn't pay the cost for both
     *  houses and hotels for a particular hotel, just the hotel. Example,
     *  if the player has boardwalk and park place, with a hotel on
     *  boardwalk and four houses on park place. A Community Chest would
     *  force them to pay 1 hotel * $115 + 4 houses * $40 = $275. */
    public void propertyMaintenance(int houseCost, int hotelCost) {
        //Code here
        int maintenance = 0;
        for (String group: _properties.keySet()) {
            for (Property prop: _properties.get(group)) {
                if (!prop.isFull() || !(prop instanceof Street)) {
                    continue;
                } else {
                    Street st = (Street) prop;
                    int totalHouses = st.getHouses();
                    // Hotel Case
                    if (totalHouses > 4) {
                        maintenance += hotelCost;
                    } else {
                        maintenance += (houseCost * totalHouses);
                    }
                }
            }
        }
        loseMoney(maintenance);
    }


    /**  Buy unowned Property and adds it into the list of properties owned by the Player */
    public void buyProperty(Property property) {
        if (_money >= property.price()) {   
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
        } else {
            //TODO Remove for FE
            System.out.println("Player does not have enough money to buy property");
            return;
        }
    }

    /** Upgrade the number of houses on PROPERTY by one. If the property
     *  already has four houses, one more will update it to a hotel.
     *  Returns true if successfully upgraded. False otherwise.
     *  Several rules are used for upgrading
     *  1. A player may not upgrade if they do not have a full set.
     *  2. A player may not upgrade if one of the properties is mortgaged.
     *  3. A player must build houses evenly (ex. cannot have a hotel
     *  on Park Place and no houses on Boardwalk), can differ at most 1.
     *  4. A player may only upgrade streets (cannot upgrade railroads
     *  or utilities.
     *  5. A Monopoly game can have only 32 houses and 12 hotels in total,
     *  if there are 0 houses left, a player cannot build houses. */
    public boolean upgradeProperty(Property upgrading) {
        HashSet<Property> properties = _properties.get(
                upgrading.getGroup());
        if (properties == null) {
            return false;
        } else if (!properties.contains(upgrading)) {
            return false;
        } else if (!upgrading.isFull()) {
            return false;
        } else if (!(upgrading instanceof Street)) {
            return false;
        } else {
            for (Property p : properties) {
                if (p.isMortgaged()) {
                    return false;
                }
            }
            Street street = (Street) upgrading;
            int cost = street.getCost(); 
            if (street.getHouses() + 1 > 5) {
                return false;
            } else if (cost > _money) {
                return false;
            } else {
                int houses = street.getHouses();
                if (houses <= 4 && _monopoly.houses() == 0) {
                    return false;
                } else if (houses == 5 && _monopoly.hotels() == 0) {
                    return false;
                }
            }
            street.setHouses(street.getHouses() + 1);
            if (isUneven(upgrading)) {
                street.setHouses(street.getHouses() - 1);
                return false;
            }
            loseMoney(cost);
            if (street.getHouses() == 5) {
                _monopoly.setHouses(_monopoly.houses() + 4);
                _monopoly.setHotels(_monopoly.hotels() - 1);
            } else {
                _monopoly.setHouses(_monopoly.houses() - 1);
            }
            return true;
        }
    }

    /** Checks a group of properties owned by a player to see if their
     *  houses differ by more than 1, returning true if so. This is to
     *  determine whether a house can be bought or sold on this property.
     *  Assumes that the player owning the property has a full set, and
     *  assumes that it is a street. */
    public boolean isUneven(Property upgrading) {
        List<Property> properties = new ArrayList<Property>(
                _properties.get(upgrading.getGroup()));
        int minHouses = ((Street) properties.get(0)).getHouses();
        int maxHouses = ((Street) properties.get(0)).getHouses();
        for (int i = 1; i < properties.size(); i++) {
            Street s = (Street) properties.get(i);
            int houses = s.getHouses();
            if (houses > maxHouses) {
                maxHouses = houses;
            }
            if (houses < minHouses) {
                minHouses = houses;
            }
        }
        return maxHouses - minHouses > 1;
    }

    /** Sells a house on PROPERTY. Selling must be done evenly so that
     *  the number of houses on a property for a group does not differ
     *  by more than 1. */
    public boolean sellHouse(Property property) {
        HashSet<Property> properties = _properties.get(
                property.getGroup());
        if (properties == null) {
            return false;
        } else if (!properties.contains(property)) {
            return false;
        } else if (!property.isFull()) {
            return false;
        } else if (!(property instanceof Street)) {
            return false;
        } else {
            Street street = (Street) property;
            int gain = street.getCost() / 2;
            int houses = street.getHouses();
            if (houses == 0) {
                return false;
            }
            if (houses > 4 && _monopoly.houses() == 0) {
                return false;
            }
            street.setHouses(houses - 1);
            houses--;
            if (isUneven(property)) {
                street.setHouses(houses + 1);
                return false;
            }
            gainMoney(gain);
            if (houses == 4) {
                _monopoly.setHouses(_monopoly.houses() - 4);
                _monopoly.setHotels(_monopoly.hotels() + 1);
            } else {
                _monopoly.setHouses(_monopoly.houses() + 1);
            }
            return true;
        }
    }

    /** Resolves effect of landing on a piece. Written to reduce
     *  code repetition. */
    public void resolveLanding() {
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

    /** Mortgage a given PROPERTY, gaining money to the player equal
     *  to the mortgage value of the property. */
    public void mortgageProperty(Property property) {
        property.mortgage(this);
    }

    /** Unmortgage a given PROPERTY, requiring the player to pay the
     *  mortgage value plus a 10% interest rate. */
    public void unmortgageProperty(Property property) {
        property.unmortgage(this);
    }

    /** Draws a Chance Card */
    public void drawChance() {
        _monopoly.drawChance().effect(this);
    }

    /** Draws a Community Chest Card */
    public void drawCommunityChest() {
        _monopoly.drawChest().effect(this);
    }

    @Override
    public boolean equals(Object obj) {
        Player other = (Player) obj;
        return other._id == this._id && other._money == this._money;
    }

    @Override
    public int hashCode() {
        return _id;
    }
}
