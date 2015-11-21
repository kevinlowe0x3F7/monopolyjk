/** Property piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Property implements BoardPiece {
    /** The name of this piece. */
    private final String _name;
    /** The group that this property belongs to. It will generally be
     *  denoted by a color in String notation with the exception of
     *  utilities and railroads. */
    private final String _group;
    /** The price to buy this property. */
    private final int _price;
    /** The number of property for this group needed to make
     *  a full set. */
    private final int _fullSet;
    /** True if this property is part of a full set because all of the
     *  same group is owned by one player. False otherwise. */
    private boolean _isFull;
    /** The number of houses on this property. Can only be [0, 5].
     *  5 houses indicate a hotel. */
    private int _houses;
    /** The player ID that this property is owned by. If this property
     *  is not owned by any player, the value is 0. */
    private int _id;
    /** The cost of rent, represented as a array, where the index of the
     *  array represents the numbers of houses that are on this piece.
     *  Example, Boardwalk would have its _rent variable be
     *  {50, 200, 600, 1400, 1700, 2000}. If the property has 1 house,
     *  the cost of rent would be $200. */
    private final int[] _rent;
    /** The value obtained from mortgaging this property. */
    private final int _mortgage;
    /** True if this property is mortgaged, false otherwise. Indicates
     *  that no rent will be paid if the property is mortgaged. */
    private boolean _isMortgaged;
    /** The cost to build a house or hotel on this property. */
    private final int _buildCost;

    public Property(String name, String group, int price, int set,
            int[] rent, int mortgage, int buildCost) {
        _name = name;
        _group = group;
        _price = price;
        _fullSet = set;
        _isFull = false;
        _houses = 0;
        _id = 0;
        _rent = rent;
        _mortgage = mortgage;
        _buildCost = buildCost;
        _isMortgaged = false;
    }

    @Override
    public int effect(Player player) {
        if (_isMortgaged) {
            return 0;
        } else if (_id == 0) {
            // Unowned, give player the opportunity to buy property.
        } else if (_group.equals("railroad")) {
            // Special case, would be implemented by checking the owner
    }

    @Override
    public String name() {
        return _name;
    }

    /** Returns the group as a String that this property belongs to. */
    public String getGroup() {
        return _group;
    }

    /** Returns the number required to make a full set. */ 
    public int getSetNum() {
        return _fullSet;
    }

    /** Returns the price. */
    public int getPrice() {
        return _price;
    }

    /** Returns true if this property is part of a full set, false
     *  otherwise. */
    public boolean isFull() {
        return _isFull;
    }

    /** Check to see if the player has a full set of property, if yes,
     *  set all isFull values of this group to true. Should be checked
     *  every time property is bought. */
    public void checkFull(Player player) {
        return;
    }

    /** Returns the number of houses on this property. */
    public int getHouses() {
        return _houses;
    }

    /** Sets the number of houses on this property based on the given
     *  value HOUSES. */
    public void setHouses(int houses) {
        if (houses > 5 || houses < 0) {
            return;
        }
        _houses = houses;
    }

    /** Returns the value for rent based on the number of houses that this
     *  property has. Also factors in whether it is part of a full set. */
    public int getRent() {
        if (_houses == 0) {
            if (_isFull) {
                return _rent[_houses] * 2;
            }
        }
        return _rent[_houses];
    }

    /** Returns the player ID that owns this property. */
    public int getID() {
        return _id;
    }

    /** Sets the player ID of this property with ID. */
    public void setID(int id) {
        _id = id;
    }

    /** Returns the value obtained from mortgaging this property. */
    public int getMortgageValue() {
        return _mortgage;
    }

    /** Returns true if this property is mortgaged, false otherwise. */
    public boolean isMortgaged() {
        return _isMortgaged;
    }

    /** Mortgage this property owned by PLAYER. */
    public void mortgage(Player player) {
        if (player.getID() != _id){
            return;
        }
        _isMortgaged = true;
        // player.increaseMoney(_mortgage);
    }

    /** Return the cost to build things on this property. */
    public int getCost() {
        return _buildCost;
    }

    /** Returns true if this property is owned, false otherwise. */
    public boolean isOwned() {
        return _id != 0;
    }
}
