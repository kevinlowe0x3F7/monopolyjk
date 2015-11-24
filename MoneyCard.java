/** Community Chests cards that give the
 *  player's money or takes the player's money
 *  @author Joseph Jiang
 */

public class MoneyCard implements CommunityChest, Chance {
	/** Name of the card */
	private String _name;
	/** Amount gain/lost in money */
	private int _amount;
	/** True if player gains money, False if player losts money */
	private boolean _arithmetic;
	/** The cards message */
	private String _message;

	public MoneyCard(String name, int amount, boolean arithmetic, String message) {
		_name = name;
		_amount = amount;
		_arithmetic = arithmetic;
		_message = message;
	}

	/** Returns the name of the card */
	public String name() {
		return _name;
	}

	/** The card's effect on the player (gain/lose) */
	public void effect(Player player) {
		if (_arithmetic) {
			player.gainMoney(_amount);
		} else {
			player.loseMoney(_amount);
		}
	}
}

		// switch (_name) {
  //           case "Bank error in your favor":
  //           	player.gainMoney(200);
  //           	break;
  //           case "Doctor's fees":
  //           	player.loseMoney(50);
  //           	break;
  //           case "From sale of stock you get $50":
  //           	player.gainMoney(50);
  //           	break;
  //           case "Holiday Fund matures":
  //           	player.gainMoney(100);
  //           	break;
  //           case "Income tax refund":
  //           	player.gainMoney(20);
  //           	break;
  //           case "Life insurance matures":
  //           	player.gainMoney(100);
  //           	break;
  //           case "Pay hospital fees of $100":
  //           	player.loseMoney(100);
  //           	break;
  //           case "Pay school fees of $150":
  //           	player.loseMoney(150);
  //           	break;
  //           case "Receive $25 consultancy fee":
  //           	player.gainMoney(25);
  //           	break;
  //           case "You have won second prize in a beauty contest":
  //           	player.gainMoney(10);
  //           	break;
  //           case "Bank pays you dividend of $50":
  //           	player.gainMoney(50);
  //           	break;

  //           //"You inherit $100"
  //           default:
  //           	player.gainMoney(100);
  //           	break;
  //       }
  //   }

