import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

import java.util.LinkedList;
/** Class to implement the main panel of the GUI, containing all parts
 *  of the GUI:board, player stats, buttons, message box (View).
 *  @author Kevin Lowe
 *
 *  Credits: Board: http://bradfrost.com/blog/post/monopoly-photoshop-template/
 *  Wood: http://www.pixeden.com/graphic-web-backgrounds/wood-pattern-background
 */
public class MainPanel extends JPanel {
    /** The Monopoly game that I get information from. */
    private Monopoly _game;

    /** The GUI that this display is in. */
    private MonopolyGUI _gui;
    /** The status String of the game, updated every time something
     *  happens. Limited to 10 lines. */
    private String _status;
    /** Character limit for the status line. Any single status line that
     *  goes over this limit will need ot be drawn on another line. */
    private static final int CHAR_LIMIT = 32;
    /** Line limit for the status box. */
    private static final int LINE_LIMIT = 12;
    /** True if there are max lines on the status. */
    private boolean _maxLines;
    private int _paintCount;

    /** Initializes all the parts. */
    public MainPanel(Monopoly game, MonopolyGUI gui) {
        _game = game;
        _gui = gui;
        setLayout(null);
        setOpaque(true);
        setBounds(0, 0, 800, 650);
        _status = "Welcome to Monopoly!\n";
        _paintCount = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Paint count: " + _paintCount);
        _paintCount++;
        // super.paintComponent(g);
        drawBackground((Graphics2D) g);
        drawBoard((Graphics2D) g);
        drawPropertyandPlayerMarkers((Graphics2D) g);
        displayStats((Graphics2D) g);
        addButtons1();
        addButtons2();
        addStatusField((Graphics2D) g);
    }
    
    /** Renders the wood.jpg background image and displays it. */
    public void drawBackground(Graphics2D g) {
        InputStream in = getClass().getResourceAsStream("wood.jpg");
        Image background = null;
        try {
            background = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error rendering background.");
        }
        g.drawImage(background, 0, 0, 800, 650, null);
    }

    /** Renders the board.jpg image and displays it. */
    public void drawBoard(Graphics2D g) {
        InputStream in = getClass().getResourceAsStream("board.jpg");
        Image board = null;
        try {
            board = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error rendering board.");
        }
        g.drawImage(board, 30, 50, 425, 425, null);
    }

    /** Draws all the markers indicating where players are at and who
     *  owns what property. */
    public void drawPropertyandPlayerMarkers(Graphics2D g) {
    }

    /** Displays the player stats, indicating ID, money amount,
     *  and location. */
    public void displayStats(Graphics2D g) {
        int startX = 15;
        Player[] players = _game.players();
        for (int i = 1; i < players.length; i++) {
            g.setColor(Monopoly.COLORS[i]);
            int yCoord = 520;
            Font font = new Font("Arial Black", Font.PLAIN, 18);
            g.setFont(font);
            g.drawString("Player " + i, startX, yCoord);
            yCoord += 25;
            if (players[i] == null) {
                g.setColor(Color.BLACK);
                g.drawString("Bankrupt", startX, yCoord);
            } else {
                Player next = players[i];
                g.drawString("$" + next.money(), startX, yCoord);
                yCoord += 25;
                String location = next.location().piece().name();
                int lastIndex = location.lastIndexOf(' ');
                if (lastIndex != -1) {
                    String location1 = location.substring(lastIndex + 1);
                    location = location.substring(0, lastIndex);
                    g.drawString(location, startX, yCoord);
                    yCoord += 15;
                    g.drawString(location1, startX, yCoord);
                } else {
                    g.drawString(location, startX, yCoord);
                }
            }
            // TODO draw marker for current player
            startX += 200;
        }

    }

    /** Add the top section of buttons. */
    public void addButtons1() {
        JButton newGame = new JButton("New Game");
        newGame.setLocation(15, 15);
        newGame.setSize(120, 20);
        newGame.addActionListener(_gui);
        add(newGame);

        JButton quit = new JButton("Quit");
        quit.setLocation(330, 15);
        quit.setSize(120, 20);
        quit.addActionListener(_gui);
        add(quit);

        JButton about = new JButton("About");
        about.setLocation(660, 15);
        about.setSize(120, 20);
        about.addActionListener(_gui);
        add(about);
    }

    /** Add the bottom section of buttons. */
    public void addButtons2() {
        // TODO switch buttons based on roll or end turn
        // switch buttons if surrendering
        JButton roll = new JButton("Roll Dice");
        roll.setLocation(480, 50);
        roll.setSize(150, 70);
        roll.addActionListener(_gui);
        add(roll);
        JButton mortgage = new JButton("Mortgage");
        mortgage.setLocation(635, 50);
        mortgage.setSize(150, 70);
        mortgage.addActionListener(_gui);
        add(mortgage);
        JButton houses = new JButton("Buy/Sell Houses");
        houses.setLocation(480, 140);
        houses.setSize(150, 70);
        houses.addActionListener(_gui);
        add(houses);
        JButton trade = new JButton("Trade");
        trade.setLocation(635, 140);
        trade.setSize(150, 70);
        trade.addActionListener(_gui);
        add(trade);
    }

    /** Add the status field. */
    public void addStatusField(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(480, 230, 315, 245, 20, 20);
        Font font = new Font("Arial Black", Font.PLAIN, 14);
        g.setFont(font);
        int x = 490;
        int y = 250;
        for (String line : splitStatus()) {
            g.drawString(line, x, y);
            y += 20;
        }
    }

    /** Add the given line to the status method. */
    public void addLine(String line) {
        int lines = 1;
        String firstLine = "";
        String secondLine = "";
        if (line.length() > CHAR_LIMIT) {
            int lastSpace = line.lastIndexOf(' ', CHAR_LIMIT);
            firstLine = line.substring(0, lastSpace);
            secondLine = line.substring(lastSpace + 1);
            lines = 2;
        }
        if (lines == 1) {
            _status += (line + '\n');
        } else {
            _status += (firstLine + '\n');
            _status += (secondLine + '\n');
        }
        if (_maxLines) {
            deleteLine();
            if (lines == 2) {
                deleteLine();
            }
        }
        if (getLines() >= LINE_LIMIT) {
            _maxLines = true;
        }
    }

    /** Returns the number of lines in the status String. */
    public int getLines() {
        int count = 0;
        for (int i = 0; i < _status.length(); i++) {
            if (_status.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }
    
    /** Delete the first line of the status, indicated by the first
     *  new line character. */
    public void deleteLine() {
        int newLine = _status.indexOf('\n');
        if (newLine == -1 || newLine == _status.length() - 1) {
            return;
        } else {
            _status = _status.substring(newLine + 1);
        }
    }

    /** Split status into several Strings based on new line characters. */
    public LinkedList<String> splitStatus() {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < _status.length(); i++) {
            String line = "";
            char next = _status.charAt(i);
            if (next == '\n') {
                result.add(line);
                line = "";
            } else {
                line += next;
            }
        }
        return result;
    }
    
}
