import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Class that implements the game (Model) for Monopoly.
 *  @author Kevin Lowe
 *  @version 1.0 11/19/2015
 */
public class Monopoly {
    /** The board for this game, represented as a circular doubly
     *  linked list. */
    private BoardNode _board;

    /** The deck of Community Chest cards. Commented out until
     *  implementation of CommunityChest objects are complete. */
    // private CommunityChest[] _chest;

    /** The deck of Chance cards. Commented out until implementation
     *  of Chance objects are complete. */
    // private Chance[] _chance;

    /** The players of this game. */
    private Player[] _players;

    /** The number of players in this game. */
    private final int _numPlayers;

    /** Index to see whose turn it is. */
    private int currentIndex;

    /** Starts a new Monopoly game with a set number of players given
     *  by NUMPLAYERS. Number of players may not be changed mid-game. */
    public Monopoly(int numPlayers) {
        try {
            initializeBoard();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException io) {
            System.out.println(io.toString());
        }
        _players = new Player[numPlayers + 1];
        _numPlayers = numPlayers;
        currentIndex = 1;
    }

    /** Returns the board of this game. */
    public BoardNode getBoard() {
        return _board;
    }

    /** Moves the index to the next player. */
    private void nextPlayer() {
        if (currentIndex == _numPlayers) {
            currentIndex = 1;
        } else {
            currentIndex += 1;
        }
    }

    /** Initializes the board as a circular doubly linked list, starting
     *  from GO. Uses boardinfo.txt file for its resource. */
    private void initializeBoard() throws FileNotFoundException,
            IOException {
        BufferedReader input = null;
        BoardNode start = new BoardNode(new GoPiece());
        BoardNode curr = start;
        try {
            input = new BufferedReader(new FileReader("boardinfo.txt"));
            String next = input.readLine();
            while (next != null) {
                String[] info = next.split(",");
                switch (info[0]) {
                case "jail":    
                    curr.setNext(new BoardNode(new Jail()));
                    break;
                case "community chest":
                    curr.setNext(new BoardNode(new CommunityChestPiece()));
                    break;
                case "chance":
                    curr.setNext(new BoardNode(new ChancePiece()));
                    break;
                case "free parking":
                    curr.setNext(new BoardNode(new FreeParking()));
                    break;
                case "go to jail":
                    curr.setNext(new BoardNode(new GoToJail()));
                    break;
                case "tax":
                    curr.setNext(new BoardNode(new TaxPiece(info[1])));
                    break;
                default:
                    curr.setNext(initializeProperty(info));
                    break;
                }
                curr = curr.next();
                next = input.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("boardinfo.txt not found");
        } catch (IOException e) {
            throw new IOException("Invalid input in boardinfo.txt file");
        }
        input.close();
        start.setPrev(curr);
        _board = start;
    }

    /** Initializes a piece of property with the given property INFO. */
    private BoardNode initializeProperty(String[] info) {
        if (info[0].equals("railroad")) {
            return new BoardNode(new Railroad(info[1], info[0],
                    200, 4, 100));
        } else if (info[0].equals("utility")) {
            return new BoardNode(new Utility(info[1],info[0], 150, 2, 75));
        } else {
            String name = info[1];
            String group = info[2];
            int price = Integer.parseInt(info[3]);
            int groupNum = Integer.parseInt(info[4]);
            int[] rent = new int[6];
            int rentIndex = 0;
            for (int i = 5; i <= 10; i++) {
                rent[rentIndex] = Integer.parseInt(info[i]);
                rentIndex++;
            }
            int mortgage = Integer.parseInt(info[11]);
            int build = Integer.parseInt(info[12]);
            return new BoardNode(new Street(name, group, price, groupNum,
                    rent, mortgage, build));
        }
    }

}
