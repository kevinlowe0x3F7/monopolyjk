import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;
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

    /** My random generator. */
    private static Random _source;

    /** The players of this game. */
    private static Player[] _players;

    /** The number of players in this game. */
    private static int _numPlayers;

    /** Index to see whose turn it is. */
    private static int currentIndex;


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
        _source = new Random();
    }

    /** Returns the board of this game. */
    public BoardNode getBoard() {
        return _board;
    }

    /** Moves the index to the next player. */
    private static void nextPlayer() {
        if (currentIndex == _numPlayers) {
            currentIndex = 1;
        } else {
            currentIndex += 1;
        }
    }

    /** Rolls a die, returning an integer between 1 and 6. */
    public static int rollDice() {
        int next = _source.nextInt(6) + 1;
        return next;
    }

    /** (Joseph) Returns the victor when the game ends */
    public static Player victor() {
        for (int i = 1; i < _players.length; i++) {
            if (_players[i] != null) {
                return _players[i];
            }
        }
        return null;
    }

    /** (Joseph) Checks if the game ended - When all players are bankrupt */
    public static boolean gameContinues() {
        int numBankRupt = 0;
        for (int i = 1; i < _players.length; i++) {
            if (_players[i].bankrupt()) {
                numBankRupt += 1;
            }
        }
        if (numBankRupt == _numPlayers - 1) {
            return false;
        } else {
            return true;
        }
    }

    /** (Joseph) Deals with the Board Piece that the player lands on */
    public static void playerLands(BoardPiece piece, Player current) {
        if (piece instanceof Property) {
            //if instance of property, then cast to Property
            Property property = (Property) piece;
            if (property.isOwned()) {
                property.effect(current);
            } else {
                current.buyProperty(property);
            }
        } else {
             piece.effect(current);
        }
    }

    /** Initializes the board as a circular doubly linked list, starting
     *  from GO. Uses boardinfo.txt file for its resource. */
    private void initializeBoard() throws FileNotFoundException, IOException {
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

    // (Joseph) Writing Main Method
    public static void main(String[] args) {
        //args = [numOfPlayers]
        int numPlayers = Integer.parseInt(args[0]);
        
        // Bounds the number of players allowed
        if (numPlayers < 2 || numPlayers > 8) {
            System.out.println("Invalid Number of Players");
            return;
        }

        Monopoly game = new Monopoly(numPlayers);
        while (gameContinues()) {
            Player currentPlayer = _players[currentIndex];
            nextPlayer();
            int rolledNum = rollDice();
            currentPlayer.movePlayer(rolledNum); //Move the Player a set number of spaces
            playerLands(currentPlayer.location().piece(), currentPlayer); //Deals with the Board Piece that the player lands on
        }
    }
}
