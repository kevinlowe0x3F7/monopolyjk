/** Main class to initialize Monopoly.
 *  @author Kevin Lowe
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please indicate the number of players" +
                    " in the args (ex. java Main 3)");
            return;
        }
        int players = Integer.parseInt(args[0]);
        if (players < 2 || players > 4) {
            System.out.println("Invalid number of players, 2-4"
                + " players allowed.");
            return;
        }
        Monopoly game = new Monopoly(players);
        MonopolyGUI m = new MonopolyGUI(game);
    }
}
