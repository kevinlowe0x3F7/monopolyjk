/** Utility piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Utility extends Property {
    public Utility(String name, String group, int price, int set,
            int mortgage, int x, int y, String pos) {
        super(name, group, price, set, mortgage, x, y, pos);
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
            return true;
        }
    }


    @Override
    public String name() {
        return super.name();
    }

    /** Returns the value of rent based on the dice roll and the factor
     *  of whether the set is full or not. */
    public int getRent(Player owner, Player current) {
        int roll = current.rolls()[0] + current.rolls()[1];
        int factor;
        if (isFull()) {
            factor = 10;
        } else {
            factor = 4;
        }
        return roll * factor;
    }

    /** Chance Card Effect for Utility -> dice roll by factor of 10 */
    public void specialEffect(Player current, int firstRoll, int secondRoll) {
        if (isMortgaged()) {
            return;
        }
        if (current.getID() == owner().getID()) {
            return;
        } else {
            int totalRoll = firstRoll + secondRoll;
            current.loseMoney(10 * totalRoll);
            owner().gainMoney(10 * totalRoll);
        }
    }
}
