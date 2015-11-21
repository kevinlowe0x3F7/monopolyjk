/** Railroad piece on the Monopoly board.
 *  @author Kevin Lowe
 */
public class Railroad extends Property {
    /** The number of railroads that this player owns. Used to
     *  calculate the amount for rent. */
    private int _railroads;

    public Railroad(String name, String group, int price, int set,
            int mortgage) {
        super(name, group, price, set, mortgage);
        _railroads = 1;
    }

    /** Returns the cost for rental on this property based on the
     *  number of railroads the player own. */
    public int getRent() {
        return 50 * _railroads;
    }

    @Override
    public void effect(Player player) {
        return;
    }

    @Override
    public String name() {
        return super.name();
    }
    
    /** Returns the number of railroads the player own. */
    public int getRailroads() {
        return _railroads;
    }

    /** Sets the number of railroads to RAILROADS. */
    public void setRailroads(int railroads) {
        _railroads = railroads;
    }
}
