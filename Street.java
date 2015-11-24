/** Street piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Street extends Property {
    /** The number of houses on this street. Can only be [0, 5].
     *  5 houses indicate a hotel. */
    private int _houses;
    /** The cost of rent, represented as an array where the index of the
     *  array represents the number of houses that are on this street.
     *  Example, Boardwalk would have its _rent variable be
     *  {50, 200, 600, 1400, 1700, 2000}. If the property has 1 house,
     *  the cost of rent would be $200. */
    private final int[] _rent;
    
    /** The cost to build a house or hotel on this property. */
    private final int _buildCost;

    public Street(String name, String group, int price, int set,
            int[] rent, int mortgage, int buildCost) {
        super(name, group, price, set, mortgage);
        _houses = 0;
        _rent = rent;
        _buildCost = buildCost;
    }

    @Override
    public void effect(Player current) {
        if (isMortgaged()) {
            return;
        }

        if (current.getID() == owner().getID()) {
            return;
        } else {
            current.loseMoney(this.getRent());
            owner().gainMoney(this.getRent());
        }
        return;
    }

    @Override
    public String name() {
        return super.name();
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
            if (isFull()) {
                return _rent[_houses] * 2;
            }
        }
        return _rent[_houses];
    }

    /** Return the cost to build house and hotels on this property. */
    public int getCost() {
        return _buildCost;
    }
}
