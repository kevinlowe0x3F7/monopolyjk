/** Utility piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Utility extends Property {
    public Utility(String name, String group, int price, int set,
            int mortgage) {
        super(name, group, price, set, mortgage);
    }

    @Override
    public void effect(Player player) {
        return;
    }

    @Override
    public String name() {
        return super.name();
    }

    /** Returns the factor for the rent, based on if it is a full set. */
    public int getFactor() {
        if (isFull()) {
            return 8;
        } else {
            return 3;
        }
    }
}
