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
    /** The value obtained from mortgaging this property. */
    private final int _mortgage;
    /** True if this property is mortgaged, false otherwise. Indicates
     *  that no rent will be paid if the property is mortgaged. */
    private boolean _isMortgaged;
    /** The player that owns this property */
    private Player _owner;
    /** The x coordinate of the property in the GUI */
    private int _x;
    /** The y coordinate of the property in the GUI */
    private int _y;
    /** The postion (horizontal or vertical) 
     *for the property in GUI */
    private String _pos;

    public Property(String name, String group, int price, int set,
            int mortgage, int x, int y, String pos) {
        _name = name;
        _group = group;
        _price = price;
        _fullSet = set;
        _isFull = false;
        _owner = null;
        _mortgage = mortgage;
        _isMortgaged = false;
        _x = x;
        _y = y;
        _pos = pos;
    }

    @Override
    public boolean effect(Player current) {
        return true;
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

    /** Returns the rent of the property */
    abstract public int getRent(Player owner, Player current);

    /** Returns true if this property is part of a full set, false
     *  otherwise. */
    public boolean isFull() {
        return _isFull;
    }

    /** Returns the x position of the property for GUI */
    public int x() {
        return _x;
    }

    /** Returns the y position of the property for GUI */
    public int y() {
        return _y;
    }

    /** Check to see if the player has a full set of property, if yes,
     *  set all isFull values of this group to true. Should be checked
     *  every time property is bought. */
    public static void checkFull(Player player, Property property) {
        String group = property._group;
        if (property.isFull()) {
            return;
        }
        HashSet<Property> properties = player.properties().get(group);
        if (properties.size() == property._fullSet) {
            for (Property p : properties) {
                p._isFull = true;
            }
        }
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
    public boolean mortgageToggle(Player player) {
        if (!isOwned()) {
            return false;
        }
        if (!_owner.equals(player)) {
            return false;
        }
        //Unmortgage Property
        if (_isMortgaged) {
            _isMortgaged = false;
            player.loseMoney(_mortgage + ((int) (_mortgage * 0.1)));
            return true;
        //Mortgage Property
        } else {    
            _isMortgaged = true;
            player.gainMoney(_mortgage);
            return true;
        }
    }

    /** Unmortgage this property owned by PLAYER. */
    // public void unmortgage(Player player) {
    //     if (!_owner.equals(player) || !_isMortgaged) {
    //         return;
    //     }
    //     _isMortgaged = false;
    //     player.loseMoney(_mortgage + ((int) (_mortgage * 0.1)));
    // }

    /** Returns true if this property is owned, false otherwise. */
    public boolean isOwned() {
        return _owner != null;
    }

    /** Returns the price of the property */
    public int price() {
        return _price;
    }

    /** Returns the Player that owns the property */
    public Player owner() {
        return _owner;
    }

    /** Returns the position of the property */
    public String position() {
        return _pos;
    }

    public void setOwner(Player owner) {
        _owner = owner;
    } 

    /** Returns the number of houses on this property. Streets will
     *  override this method. */
    public int getHouses() {
        return 0;
    }
}
