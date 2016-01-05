/** Handles the card that charges the 
 *  player for the number of houses
 *  and hotels owned for each property
 *  @author Joseph Jiang
 */

public class PropertyMaintenanceCard implements CommunityChest, Chance {
	/** Name of the card */
	private String _name;
	/** Amount charged for each hotel */
	private int _hotelCost;
	/** Amount charged for each house */
	private int _houseCost;
	/** Message of the card */
	private String _message;

	public PropertyMaintenanceCard(String name, int hotelCost, int houseCost, String message){
		_name = name;
		_hotelCost = hotelCost;
		_houseCost = houseCost;
		_message = message;
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}

	/** Handles the effect of charging the player
	 *  for the cost of each hotel and house */
	public void effect(Player player) {
		player.propertyMaintenace(_houseCost, _houseCost);
	}
}
