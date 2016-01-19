/** Handles the jump from the player's
 *  current location to Jail
 *  @author Joseph Jiang
 */

public class JumpCard implements CommunityChest, Chance {
	/** Name of the card */
	private String _name;
	/** Destination of Jail */
	private String _destination;
	/** Message of the card */
	private String _message;

	public JumpCard(String name, String destination, String message) {
		_name = name;
		_destination = destination;
		_message = message;
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}

	/** Handles the jump to the jail */
	public void effect(Player player) {
		player.jumpPlayer(_destination);
		player.inJail(true);
		if (player.game().gui() != null) {
		    player.game().gui().panel().status().addLine(_name);
		    player.game().gui().panel().status().addLine(_message);
		    player.game().nextPlayer();
		    player.game().gui().panel().buttons().roll().setText("Roll Dice");
        }
	}
}
