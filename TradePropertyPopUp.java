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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Class to implement the trade property pop up of the GUI, containing 
 *  description of the property and button choices
 *  @author Joseph
 *
 *  Credits: http://monopoly.wikia.com/wiki/Property
 */


public class TradePropertyPopUp {


	// /** Board image. */
 //    private Image _board;

 //    /** Background image. */
 //    private Image _background;

 //    /** The game GUI */
 //    private MonopolyGUI _gui;

 //    /** On button click -> paint the property */
 //    private boolean _paintClick;

 //    /** Names of the properties that was clicked on by current*/
 //    private Property[] _propertyListCurrent;

 //    /** Names of the properties thta was clicked on by offered */
 //   	private Property[] _propertyListOffered;

 //   	/** The current player making the trade */
 //   	private Player _current;

 //   	/** The offered player making the trade */
 //   	private Player _offered;

 //    /** The Monopoly game */
 //    private Monopoly _game;

 //    /** The Mortgage Initialized */
 //    private boolean _initialized;

 //    /** HashMap containing all of the property locations based on the
 //     *  X and Y coordinates from boardlocs.txt. */
 //    private HashMap<String, Location> _locations;


 //    public TradePropertyPopUp(MonopolyGUI gui, Monopoly game, Player current, Player offered) {
	// 	super();
	// 	_gui = gui;
 //        _game = game;
 //        _current = current;
 //        _current = current;

 //        setPropertyLocations();
 //        this.setOpaque(false);
 //        this.setLayout(null);
 //        InputStream in1 = getClass().getResourceAsStream(
 //                "resources/wood.jpg");
 //        try {
 //            _background = ImageIO.read(in1);
 //        } catch (IOException e) {
 //            System.out.println("error loading background image");
 //            System.exit(1);
 //        }
 //        InputStream in2 = getClass().getResourceAsStream(
 //                "resources/board.jpg");
 //        try {
 //            _board = ImageIO.read(in2);
 //        } catch (IOException e) {
 //            System.out.println("error loading board image.");
 //            System.exit(1);
 //        }
	// }


	// @Override
 //    protected void paintComponent(Graphics g) {
 //        g.drawImage(_background, 0, 0, 800, 650, null);
 //        drawBoard((Graphics2D) g);
 //        drawPropertyButtons((Graphics2D) g);
 //        drawButtons((Graphics2D) g);
 //        if (_paintClick) {
 //            clickDraw((Graphics2D) g);
 //        }
 //        drawPropertyMarkers((Graphics2D) g);

 //    }

 //    /** Draws the board itself. */
 //    private void drawBoard(Graphics2D g) {
 //        g.drawImage(_board, 30, 40, 425, 425, null);
 //    }

 //    /** Draws the Graphics Jbuttons for the GUI */
 //    private void drawButtons(Graphics2D g) {

 //    }
	






 //    /** Draw property markers, indicating who owns which property. */
 //    private void drawPropertyMarkers(Graphics2D g) {
 //        Player[] players = _game.players();
 //        for (int i = 1; i < players.length; i++) {
 //            Player next = players[i];
 //            if (next == null) {
 //                continue;
 //            }
 //            for (String group : next.properties().keySet()) {
 //                for (Property p : next.properties().get(group)) {
 //                    g.setColor(Monopoly.COLORS[i]);
 //                    Location loc = _locations.get(p.name());
 //                    int x = loc.x;
 //                    int y = loc.y;
 //                    if (loc.side.equals("top")) {
 //                        y -= 13;
 //                        g.fillRoundRect(x + 3, y, SHORT - 3, 8, ARC, ARC);
 //                        if (p.isFull()) {
 //                            g.drawRect(loc.x, loc.y, SHORT, LONG);
 //                        }
 //                        if (p.isMortgaged()) {
 //                            g.setColor(new Color(155, 0, 0));
 //                            Font font = new Font("Arial",
 //                                    Font.BOLD, 25);
 //                            g.setFont(font);
 //                            g.drawString("M", loc.x + (SHORT / 4),
 //                                    loc.y + (LONG / 2) + 5);
 //                        }
 //                    } else if (loc.side.equals("left")) {
 //                        x -= 13;
 //                        g.fillRoundRect(x, y + 3, 8, SHORT - 3, ARC, ARC);
 //                        if (p.isFull()) {
 //                            g.drawRect(loc.x, loc.y, LONG, SHORT);
 //                        }
 //                        if (p.isMortgaged()) {
 //                            g.setColor(new Color(155, 0, 0));
 //                            Font font = new Font("Arial",
 //                                    Font.BOLD, 25);
 //                            g.setFont(font);
 //                            g.drawString("M", loc.x + (SHORT / 4),
 //                                    loc.y + (LONG / 2));
 //                        }
 //                    } else if (loc.side.equals("right")) {
 //                        x += (LONG + 5);
 //                        g.fillRoundRect(x, y + 3, 8, SHORT - 3, ARC, ARC);
 //                        if (p.isFull()) {
 //                            g.drawRect(loc.x, loc.y, LONG, SHORT);
 //                        }
 //                        if (p.isMortgaged()) {
 //                            g.setColor(new Color(155, 0, 0));
 //                            Font font = new Font("Arial",
 //                                    Font.BOLD, 25);
 //                            g.setFont(font);
 //                            g.drawString("M", loc.x + (SHORT / 2),
 //                                    loc.y + (LONG / 2));
 //                        }
 //                    } else {
 //                        y += (LONG + 5);
 //                        g.fillRoundRect(x + 3, y, SHORT - 3, 8, ARC, ARC);
 //                        if (p.isFull()) {
 //                            g.drawRect(loc.x, loc.y, SHORT, LONG);
 //                        }
 //                        if (p.isMortgaged()) {
 //                            g.setColor(new Color(155, 0, 0));
 //                            Font font = new Font("Arial",
 //                                    Font.BOLD, 25);
 //                            g.setFont(font);
 //                            g.drawString("M", loc.x + (SHORT / 4),
 //                                    loc.y + (LONG / 2) + 15);
 //                        }
 //                    }
 //                }
 //            }
 //        }
 //    }



	// /** Initializes the locations variable. */
 //    private void setPropertyLocations() {
 //        _locations = new HashMap<String, Location>();
 //        BufferedReader input = null;
 //        try {
 //            input = new BufferedReader(new FileReader(
 //                        "resources/boardlocs.txt"));
 //            String next = input.readLine();
 //            while (next != null) {
 //                String[] info = next.split(",");
 //                int x = Integer.parseInt(info[1]);
 //                int y = Integer.parseInt(info[2]);
 //                _locations.put(info[0], new Location(x, y, info[3]));
 //                next = input.readLine();
 //            }
 //            input.close();
 //        } catch (FileNotFoundException e) {
 //            System.out.println("Error: boardlocs.txt not found");
 //        } catch (IOException e) {
 //            System.out.println("Error:Invalid input in boardlocs.txt file");
 //        }
 //    }




 //    /** Draws the property that was clicked on */
 //    private void clickDraw(Graphics2D g) {
 //       	if ()
 //        g.setColor(Color.BLACK);
 //        if (_property.position().equals("vert")) {
 //            g.fillRoundRect(_property.x(), _property.y(), 34, 57, 1, 1);
 //        } else {
 //            g.fillRoundRect(_property.x(), _property.y(), 57, 34, 1, 1);            
 //        }
 //    }


 //    /** Takes care of mortgage pop up buttons */
 //    @Override
 //    public void actionPerformed(ActionEvent e) {
 //        String actionPerformed = e.getActionCommand();
 //        if (actionPerformed.equals(""))

 //        _paintClick = true;
 //        for (BoardNode curr = _game.getBoard().next(); 
 //            !(curr.piece().name().equals("Go")); curr = curr.next()) {
 //            if (curr.piece() instanceof Property) {
 //                if (curr.piece().name().equals(actionPerformed)) {
 //                    _property = (Property) curr.piece();
 //                }
 //            }
 //        }
 //        repaint();
 //    }

 //    /** Gets the property that was clicked */
 //    public Property property() {
 //        return _property;
 //    }
}



































