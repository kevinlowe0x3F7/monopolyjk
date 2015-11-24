/** Utility piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Utility extends Property {
    public Utility(String name, String group, int price, int set,
            int mortgage) {
        super(name, group, price, set, mortgage);
    }

    @Override
    public void effect(Player current) {
        if (isMortgaged()) {
            return;
        }
        // Player payer = players[turn];
        // Player owner = players[this.getID()];
        if (current.getID() == owner().getID()) {
            return;
        } else {
            // payer.loseMoney(getRent(payer.getLastRoll()));
            // owner.gainMoney(getRent(payer.getLastRoll()));
        }
    }

    @Override
    public String name() {
        return super.name();
    }

    /** Returns the value of rent based on the dice roll and the factor
     *  of whether the set is full or not. */
    public int getRent(int roll) {
        int factor;
        if (isFull()) {
            factor = 10;
        } else {
            factor = 4;
        }
        return roll * factor;
    }
}
