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

    /** Starts a new Monopoly game with a set number of players given
     *  by NUMPLAYERS. Number of players may not be changed mid-game. */
    public Monopoly(int numPlayers) {
        initializeBoard();
        _numPlayers = numPlayers;
    }

    /** Returns the board of this game. */
    public BoardNode getBoard() {
        return _board;
    }

    /** Initializes the board as a circular doubly linked list, starting
     *  with GO. */
    private void initializeBoard() {
        BoardNode start = new BoardNode(new GoPiece());
        BoardNode curr = start;
        curr.setNext(new BoardNode(new Property("Mediterranean Avenue",
        "brown", 60, 2, new int[] {2, 10, 30, 90, 160, 250}, 30, 50)));
        curr = curr.next();
        curr.setNext(new BoardNode(new CommunityChestPiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Baltic Avenue", "brown",
            60, 2, new int[] {4, 20, 60, 180, 320, 450}, 30, 50)));
        curr = curr.next();
        curr.setNext(new BoardNode(new TaxPiece("Income Tax")));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Reading Railroad",
        "railroad", 200, 4, null, 100, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Oriental Avenue",
        "light blue", 100, 3, new int[] {6, 30, 90, 270, 400, 550}, 50, 50)));
        curr = curr.next();
        curr.setNext(new BoardNode(new ChancePiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Vermont Avenue",
        "light blue", 100, 3, new int[] {6, 30, 90, 270, 400, 550}, 50, 50)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Connecticut Avenue",
        "light blue", 120, 3, new int[] {8, 40, 100, 300, 450, 600}, 60, 50)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Jail()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("St. Charles Place",
        "pink", 140, 3, new int[] {10, 50, 150, 450, 625, 750}, 70, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Electric Company",
        "utility", 150, 2, null, 75, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("States Avenue",
        "pink", 140, 3, new int[] {10, 50, 150, 450, 625, 750}, 70, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Virginia Avenue",
        "pink", 160, 3, new int[] {12, 60, 180, 500, 700, 900}, 80, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Pennsylvania Railroad",
            "railroad", 200, 4, null, 100, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("St. James Place",
        "orange", 180, 3, new int[] {14, 70, 200, 550, 750, 950}, 90, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new CommunityChestPiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Tennessee Avenue",
        "orange", 180, 3, new int[] {14, 70, 200, 550, 750, 950}, 90, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("New York Avenue",
        "orange", 200, 3, new int[] {16, 80, 220, 600, 800, 1000}, 100, 100)));
        curr = curr.next();
        curr.setNext(new BoardNode(new FreeParking()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Kentucky Avenue",
        "red", 220, 3, new int[] {18, 90, 250, 700, 875, 1050}, 110, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new ChancePiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Indiana Avenue",
        "red", 220, 3, new int[] {18, 90, 250, 700, 875, 1050}, 110, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Illinois Avenue",
        "red", 240, 3, new int[] {20, 100, 300, 750, 925, 1100}, 120, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("B. & O. Railroad",
            "railroad", 200, 4, null, 100, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Atlantic Avenue",
        "yellow", 260, 3, new int[] {22, 110, 330, 800, 975, 1150}, 130, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Ventnor Avenue",            "yellow", 260, 3, new int[] {22, 110, 330, 800, 975, 1150}, 130, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Water Works",
        "utility", 150, 2, null, 75, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Marvin Gardens",
        "yellow", 280, 3, new int[] {24, 120, 360, 825, 1025, 1200}, 140, 150)));
        curr = curr.next();
        curr.setNext(new BoardNode(new GoToJail()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Pacific Avenue",
        "green", 300, 3, new int[] {26, 130, 390, 900, 1100, 1275}, 150, 200)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("North Carolina Avenue",            "green", 300, 3, new int[] {26, 130, 390, 900, 1100, 1275}, 150, 200)));
        curr = curr.next();
        curr.setNext(new BoardNode(new CommunityChestPiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Pennsylvania Avenue",
        "green", 320, 3, new int[] {28, 150, 450, 1000, 1200, 1400}, 160, 200)));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Short Line",
            "railroad", 200, 4, null, 100, 0)));
        curr = curr.next();
        curr.setNext(new BoardNode(new ChancePiece()));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Park Place",
        "blue", 350, 2, new int[] {35, 175, 500, 1100, 1300, 1500}, 175, 200)));
        curr = curr.next();
        curr.setNext(new BoardNode(new TaxPiece("Luxury Tax")));
        curr = curr.next();
        curr.setNext(new BoardNode(new Property("Boardwalk",
        "blue", 400, 2, new int[] {50, 200, 600, 1400, 1700, 2000}, 200, 200)));
        curr = curr.next();
        start.setPrev(curr);
        _board = start;
    }
}
