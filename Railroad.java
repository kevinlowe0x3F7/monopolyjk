/** Railroad piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Railroad extends Property {
    public Railroad(String name, String group, int price, int set,
            int mortgage) {
        super(name, group, price, set, mortgage);
    }

    /** Returns the cost for rental on this property based on the
     *  number of railroads the OWNER owns. */
    public int getRent(Player owner) {
        // switch (owner.railroads()) {
        //      case 1: return 25;
        //      case 2: return 50;
        //      case 3: return 100;
        //      case 4: return 200;
        // }
        return 25;
    }

    @Override
    public void effect(Player current) {
        // Player payer = players[turn];
        // Player owner = players[this.getID()];
        if (current.getID() == owner().getID()) {
            return;
        } else {
            // payer.loseMoney(getRent(owner));
            // owner.gainMoney(getRent(owner));
        }
    }

    @Override
    public String name() {
        return super.name();
    }
}
