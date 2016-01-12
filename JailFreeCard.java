/** Handles when a Get out of Jail Free 
 *  Card is drawn
 *  @author Joseph Jiang
 */

public class JailFreeCard implements CommunityChest, Chance {
	/** Name of the card */
	private final String _name = "Get Out of Jail Free";

	/** Handles the effect of the card */
	public void effect(Player player) {
		player.jailFree(true);
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}
}