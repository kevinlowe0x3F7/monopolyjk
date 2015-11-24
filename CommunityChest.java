/** Interface for all community cards
 *  drawed by players
 *  @author Joseph Jiang
 */
public interface CommunityChest {

	/** Card's effect on player upon drawing the card */
	public abstract void effect(Player player);

	/** Name of the card drawn */
	public abstract String name();
}