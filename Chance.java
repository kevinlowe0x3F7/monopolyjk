/** Interface for all chance cards
 *  drawn by players
 *  @author Joseph Jiang
 */

public interface Chance {

	/** Effect of card on the current player */
	public void effect(Player player);

	/** Returns the name of the chance card */
	public String name();
}