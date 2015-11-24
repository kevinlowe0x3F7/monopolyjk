/** class that handles all traversal that 
 *  come from Chance and Community Chest cards
 *  @author Joseph Jiang
 */
public class TraversalCard implements Chance, CommunityChest {
	/** Name of the Traversal Card */
	private String _name;

	/** Destination of the traversal */
	private String _destination;

	/** Message of the traversal card */
	private String _message;

	public TraversalCard(String name, String destination, String message) {
		_name = name;
		_destination = destination;
		_message = message;
	}

	/** Handles the effect of the card traversal */
	public void effect(Player player) {
		player.traversePlayer(_destination);
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}

	public String destination() {
		return _destination;
	}
}