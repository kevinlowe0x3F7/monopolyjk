import java.util.HashSet;

/** Property piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public abstract class Property implements BoardPiece {
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
    /** The player ID that this property is owned by. If this property
     *  is not owned by any player, the value is 0. */
    private int _id;
    /** The value obtained from mortgaging this property. */
    private final int _mortgage;
    /** True if this property is mortgaged, false otherwise. Indicates
     *  that no rent will be paid if the property is mortgaged. */
    private boolean _isMortgaged;
    /** The player that ownes this property */
    private Player _owner;

    public Property(String name, String group, int price, int set,
            int mortgage) {
        _name = name;
        _group = group;
        _price = price;
        _fullSet = set;
        _isFull = false;
        //_id = 0;
        _owner = null;
        _mortgage = mortgage;
        _isMortgaged = false;
    }

    @Override
    public void effect(Player current) {
        return;
    }

    @Override
    public String name() {
        return _name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Property)) {
            return false;
        } else {
            Property other = (Property) obj;
            return this._name.equals(other._name);
        }
    }

    @Override
    public int hashCode() {
        return _name.hashCode();
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
    public static void checkFull(Player player, Property property) {
        String group = property._group;
        HashSet<Property> properties = player.properties().get(group);
        if (properties.size() == property._fullSet) {
            for (Property p : properties) {
                p._isFull = true;
            }
        }
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

    /** Returns true if this property is owned, false otherwise. */
    public boolean isOwned() {
        return _owner != null;
    }

    /** Returns the price of the property */
    public int price() {
        return _price;
    }

    /** (Joseph) Returns the Player that owns the property */
    public Player owner() {
        return _owner;
    }

    public void setOwner(Player owner) {
        _owner = owner;
    } 
}
