/** Railroad piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Railroad extends Property {
    public Railroad(String name, String group, int price, int set,
            int mortgage, int x, int y, String pos) {
        super(name, group, price, set, mortgage, x, y, pos);
    }

    /** Returns the cost for rental on this property based on the
     *  number of railroads the OWNER owns. */
    public int getRent(Player owner, Player current) {
        switch (owner().railroads()) {
             case 1: return 25;
             case 2: return 50;
             case 3: return 100;
             case 4: return 200;
        }
        return 25;
    }

    @Override
    public boolean effect(Player current) {
        if (isMortgaged()) {
            return false;
        }
        if (current.getID() == owner().getID()) {
            return false;
        } else {
            int rent = getRent(owner(), current);
            current.loseMoney(rent);
            owner().gainMoney(rent);
            return false;
        }
    }

    /** Chance Card Effect for RailRoad -> rent doubled */
    public void specialEffect(Player current) {
        if (current.getID() == owner().getID()) {
            return;
        } else {
            current.loseMoney(2 * getRent(owner(), current));
            owner().gainMoney(2 * getRent(owner(), current));
        }
    }

    @Override
    public String name() {
        return super.name();
    }
}
