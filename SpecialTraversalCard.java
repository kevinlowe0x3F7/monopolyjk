/** Handles special cases of traversal cards
 *  when the money cost for landing on railroards
 *  and utilities cost more
 *  @author Joseph Jiang
 */

public class SpecialTraversalCard implements Chance {
	/** Name of the Traversal Card */
	private String _name;

	/** Destination of the traversal */
	private String _destination;

	/** Message of the traversal card */
	private String _message;

	public SpecialTraversalCard(String name, String destination, String message) {
		_name = name;
		_destination = destination;
		_message = message;
	}

	/** Handles the effect of the card traversal */
	public void effect(Player player) {
		player.specialTraversePlayer(_destination);
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}
}