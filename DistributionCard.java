/** Handles the Distribution card effect on 
 *  all the players in the game
 *  @author Joseph Jiang
 */

public class DistributionCard implements CommunityChest, Chance {
	/** Name of the card */
	private String _name;
	/** Amount take/given to players */
	private int _amountDistrib;
	/** Message of the card */
	private String _message;
	/** The Monopoly object that contains all things in the game */
	private Monopoly _monopoly;
	/** True if collecting money from other players
	* False if playing money to each player */
	private boolean _collecting;

	public DistributionCard(String name, int amountDistrib, String message, boolean collecting, Monopoly monopoly) {
		_name = name;
		_amountDistrib = amountDistrib;
		_message = message;
		_collecting = collecting;
		_monopoly = monopoly;
	}


	/** Handles the effect of distributing money */
	public void effect(Player player) {
		Player[] distributers = _monopoly.players();
		if (_collecting) {
			for (int i = 1; i < distributers.length; i++) {
				if (distributers[i].getID() != player.getID()) {
					distributers[i].loseMoney(_amountDistrib);
					player.gainMoney(_amountDistrib);
				}
			}
		} else {
			for (int i = 1; i < distributers.length; i++) {
				if (distributers[i].getID() != player.getID()) {
					distributers[i].gainMoney(_amountDistrib);
					player.loseMoney(_amountDistrib);
				}
			}
		}
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}
}