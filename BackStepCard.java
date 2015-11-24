/** Handles the case when the player
 *  is forced to backstep
 *  @author Joseph Jiang
 */

public class BackStepCard implements Chance {
	/** Name of the card */
	private String _name;

	/** Number of spaces back stepped */
	private int _numSpaces;

	public BackStepCard(String name, int numSpaces) {
		_name = name;
		_numSpaces = numSpaces;
	}
	
	/** Handles the effect of the player backstepping */
	public void effect(Player player) {
		player.backstep(_numSpaces);
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}
}