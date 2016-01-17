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
		String line = "Player " + player.getID() + " has earned a " +
		    "Get Out of Jail Free card.";
		if (player.game().gui() != null) {
		    player.game().gui().panel().status().addLine(line);
        }
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}
}
