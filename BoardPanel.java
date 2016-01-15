import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/** Class for the board panel which contains the image and any markers to
 *  help indicate where players are and who owns which property.
 *  @author Kevin Lowe
 */
public class BoardPanel extends JPanel {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;
    /** Board image. */
    private Image _board;
    /** Long length of a short piece on the board.jpg image (ex: property,
     *  chance, community chest). Can be either the width of the height
     *  depending on where the piece is on the board. Also the size of the
     *  square pieces. */
    private static final int LONG = 57;
    /** Short length of a short piece on the board.jpg image. */
    private static final int SHORT = 34;
    /** Arc length for the property markers. */
    private static final int ARC = 1;
    /** Radius of the player marker circle. */
    private static final int RADIUS = 10;
    /** HashMap containing all of the property locations based on the
     *  X and Y coordinates from boardlocs.txt. */
    private HashMap<String, Location> _locations;

    public BoardPanel(Monopoly game) {
        super();
        _game = game;
        this.setOpaque(false);
        this.setLayout(null);
        setPropertyLocations();
        InputStream in = getClass().getResourceAsStream(
                "resources/board.jpg");
        try {
            _board = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("error loading board image.");
            System.exit(1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("board and markers repainted");
        drawBoard((Graphics2D) g);
        drawPropertyMarkers((Graphics2D) g);
        drawPlayerMarkers((Graphics2D) g);
    }

    /** Draws the board itself. */
    private void drawBoard(Graphics2D g) {
        g.drawImage(_board, 30, 40, 425, 425, null);
    }

    /** Draw property markers, indicating who owns which property. */
    private void drawPropertyMarkers(Graphics2D g) {
        Player[] players = _game.players();
        for (int i = 1; i < players.length; i++) {
            Player next = players[i];
            if (next == null) {
                continue;
            }
            for (String group : next.properties().keySet()) {
                for (Property p : next.properties().get(group)) {
                    g.setColor(Monopoly.COLORS[i]);
                    Location loc = _locations.get(p.name());
                    int x = loc.x;
                    int y = loc.y;
                    if (loc.side.equals("top")) {
                        y -= 13;
                        g.fillRoundRect(x + 3, y, SHORT - 3, 8, ARC, ARC);
                        if (p.isFull()) {
                            g.drawRect(loc.x, loc.y, SHORT, LONG);
                        }
                        if (p.isMortgaged()) {
                            g.setColor(new Color(155, 0, 0));
                            Font font = new Font("Arial",
                                    Font.BOLD, 25);
                            g.setFont(font);
                            g.drawString("M", loc.x + (SHORT / 4),
                                    loc.y + (LONG / 2) + 5);
                        }
                    } else if (loc.side.equals("left")) {
                        x -= 13;
                        g.fillRoundRect(x, y + 3, 8, SHORT - 3, ARC, ARC);
                        if (p.isFull()) {
                            g.drawRect(loc.x, loc.y, LONG, SHORT);
                        }
                        if (p.isMortgaged()) {
                            g.setColor(new Color(155, 0, 0));
                            Font font = new Font("Arial",
                                    Font.BOLD, 25);
                            g.setFont(font);
                            g.drawString("M", loc.x + (SHORT / 4),
                                    loc.y + (LONG / 2));
                        }
                    } else if (loc.side.equals("right")) {
                        x += (LONG + 5);
                        g.fillRoundRect(x, y + 3, 8, SHORT - 3, ARC, ARC);
                        if (p.isFull()) {
                            g.drawRect(loc.x, loc.y, LONG, SHORT);
                        }
                        if (p.isMortgaged()) {
                            g.setColor(new Color(155, 0, 0));
                            Font font = new Font("Arial",
                                    Font.BOLD, 25);
                            g.setFont(font);
                            g.drawString("M", loc.x + (SHORT / 2),
                                    loc.y + (LONG / 2));
                        }
                    } else {
                        y += (LONG + 5);
                        g.fillRoundRect(x + 3, y, SHORT - 3, 8, ARC, ARC);
                        if (p.isFull()) {
                            g.drawRect(loc.x, loc.y, SHORT, LONG);
                        }
                        if (p.isMortgaged()) {
                            g.setColor(new Color(155, 0, 0));
                            Font font = new Font("Arial",
                                    Font.BOLD, 25);
                            g.setFont(font);
                            g.drawString("M", loc.x + (SHORT / 4),
                                    loc.y + (LONG / 2) + 15);
                        }
                    }
                }
            }
        }
    }

    /** Draw player markers, indicating each players' location. */
    private void drawPlayerMarkers(Graphics2D g) {
        Player[] players = _game.players();
        for (int i = 1; i < players.length; i++) {
            Player next = players[i];
            if (next == null) {
                continue;
            }
            Location loc = _locations.get(next.location().piece().name());
            if (loc == null) {
                loc = _locations.get(getCardLoc(next));
            }
            g.setColor(Monopoly.COLORS[i]);
            drawPlayerMarker(g, loc.x, loc.y, loc.side);
        }
        Player current = _game.current();
        Location loc = _locations.get(current.location().piece().name());
        if (loc == null) {
            loc = _locations.get(getCardLoc(current));
        }
        g.setColor(Monopoly.COLORS[current.getID()]);
        drawPlayerMarker(g, loc.x, loc.y, loc.side);
    }
    
    /** Draw the actual player marker circle based on a x and y coordinate
     *  and an orientation. */
    private void drawPlayerMarker(Graphics2D g, int x, int y, String s) {
            if (s.equals("top") || s.equals("bottom")) {
                x += (SHORT / 2 - RADIUS);
                y += (LONG / 2 - RADIUS);
                g.fillOval(x, y, RADIUS * 2, RADIUS * 2);
            } else {
                x += (LONG / 2 - RADIUS);
                y += (SHORT / 2 - RADIUS);
                g.fillOval(x, y, RADIUS * 2, RADIUS * 2);
            }
    }

    /** Gets the correct community chest or chance based on the player's
     *  location, determined by the next location. */
    private String getCardLoc(Player player) {
        switch (player.location().next().piece().name()) {
            case "Baltic Avenue":
                return "Community Chest Bottom";
            case "Vermont Avenue":
                return "Chance Bottom";
            case "Tennessee Avenue":
                return "Community Chest Left";
            case "Indiana Avenue":
                return "Chance Top";
            case "Pennsylvania Avenue":
                return "Community Chest Right";
            case "Park Place":
                return "Chance Right";
            default:
                return null;
        }
    }

    /** Initializes the locations variable. */
    private void setPropertyLocations() {
        _locations = new HashMap<String, Location>();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(
                        "resources/boardlocs.txt"));
            String next = input.readLine();
            while (next != null) {
                String[] info = next.split(",");
                int x = Integer.parseInt(info[1]);
                int y = Integer.parseInt(info[2]);
                _locations.put(info[0], new Location(x, y, info[3]));
                next = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: boardlocs.txt not found");
        } catch (IOException e) {
            System.out.println("Error:Invalid input in boardlocs.txt file");
        }
    }
}
