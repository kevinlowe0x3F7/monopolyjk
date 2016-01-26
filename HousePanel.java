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



/** Class to implement the upgrading/selling house pop up of the GUI, containing 
 *  description of the property and button choices
 *  @author Joseph
 *
 *  Credits: http://monopoly.wikia.com/wiki/Property
 */


public class HousePanel extends JPanel implements ActionListener {

    /** Board image. */
    private Image _board;

    /** Background image. */
    private Image _background;

    /** The game GUI */
    private MonopolyGUI _gui;

    /** On button click -> paint the property */
    private boolean _paintClick;

    /** Name of the property that was clicked on */
    private Property _property;

    /** The Monopoly game */
    private Monopoly _game;

    /** The Mortgage Initialized */
    private boolean _initialized;



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
    /** Width and height of the house squares. */
    private static final int HOUSE = 6;
    /** Width of a hotel. */
    private static final int HOTEL = 20;
    /** HashMap containing all of the property locations based on the
     *  X and Y coordinates from boardlocs.txt. */
    private HashMap<String, Location> _locations;


    
    public HousePanel(MonopolyGUI gui, Monopoly game) {
    	super();
		_gui = gui;
		_game = game;
        setPropertyLocations();
        this.setOpaque(false);
        this.setLayout(null);
        InputStream in1 = getClass().getResourceAsStream(
                "resources/wood.jpg");
        try {
            _background = ImageIO.read(in1);
        } catch (IOException e) {
            System.out.println("error loading background image");
            System.exit(1);
        }
        InputStream in2 = getClass().getResourceAsStream(
                "resources/board.jpg");
        try {
            _board = ImageIO.read(in2);
        } catch (IOException e) {
            System.out.println("error loading board image.");
            System.exit(1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(_background, 0, 0, 800, 650, null);
        drawBoard((Graphics2D) g);
        drawButtons((Graphics2D) g);
        drawPropertyButtons((Graphics2D) g);
        drawPropertyMarkers((Graphics2D) g);
        if (_paintClick) {
        	clickDraw((Graphics2D) g);
        }
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
                        if (p instanceof Street) {
                            g.setColor(Color.WHITE);
                            int increment = 8;
                            Street street = (Street) p;
                            int x1 = loc.x + 26;
                            int y1 = loc.y + 46;
                            int houses = street.getHouses();
                            if (houses < 5) {
                                for (int j = 0; j < houses; j++) {
                                    g.fillRect(x1, y1, HOUSE, HOUSE + 3);
                                    x1 -= increment;
                                }
                            } else {
                                g.fillRect(loc.x + 7, y1,
                                        HOTEL, HOUSE + 3);
                            }
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
                        if (p instanceof Street) {
                            g.setColor(Color.WHITE);
                            int increment = 8;
                            Street street = (Street) p;
                            int x1 = loc.x + 46;
                            int y1 = loc.y + 2;
                            int houses = street.getHouses();
                            if (houses < 5) {
                                for (int j = 0; j < houses; j++) {
                                    g.fillRect(x1, y1, HOUSE + 3, HOUSE);
                                    y1 += increment;
                                }
                            } else {
                                g.fillRect(x1, loc.y + 7,
                                        HOUSE + 3, HOTEL);
                            }
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
                        if (p instanceof Street) {
                            g.setColor(Color.WHITE);
                            int increment = 8;
                            Street street = (Street) p;
                            int x1 = loc.x + 2;
                            int y1 = loc.y + 26;
                            int houses = street.getHouses();
                            if (houses < 5) {
                                for (int j = 0; j < houses; j++) {
                                    g.fillRect(x1, y1, HOUSE + 3, HOUSE);
                                    y1 -= increment;
                                }
                            } else {
                                g.fillRect(x1, loc.y + 7,
                                        HOUSE + 3, HOTEL);
                            }
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
                        if (p instanceof Street) {
                            g.setColor(Color.WHITE);
                            int increment = 8;
                            Street street = (Street) p;
                            int x1 = loc.x + 2;
                            int y1 = loc.y + 2;
                            int houses = street.getHouses();
                            if (street.getHouses() < 5) {
                                for (int j = 0; j < houses; j++) {
                                    g.fillRect(x1, y1, HOUSE, HOUSE + 3);
                                    x1 += increment;
                                }
                            } else {
                                g.fillRect(loc.x + 7, loc.y + 2,
                                        HOTEL, HOUSE + 3);
                            }
                        }
                    }
                }
            }
        }
    }


    /** Draws the graphic board onto the JPanel */ 
    private void drawBoard(Graphics2D g) {
        g.drawImage(_board, 30, 40, 425, 425, null);
    }


    /** Draws the JButtons on to the Board */
    private void drawButtons(Graphics2D g) {
      	JButton buyHouse = new JButton("Upgrade House");
    	buyHouse.setLocation(500, 40);
    	buyHouse.setSize(250, 50);
    	buyHouse.addActionListener(_gui);
    	add(buyHouse);
    	JButton sellHouse = new JButton("Sell House");
    	sellHouse.setLocation(500, 110);
    	sellHouse.setSize(250, 50);
    	sellHouse.addActionListener(_gui);
    	add(sellHouse);
    	JButton exit = new JButton("Exit");
    	exit.setLocation(500, 180);
    	exit.setSize(250, 50);
    	exit.addActionListener(_gui);
    	add(exit);
    }

    /** Draws the Buttons onto each property */
    private void drawPropertyButtons(Graphics2D g) {
    	//Top and Bottom properties
    	for (int i = 1; i < 15; i++) {
    		JButton button = new JButton("");
    		if (i == 1) {
    			button = new JButton("Kentucky Avenue");
				button.setLocation(88, 40);
    		} else if (i == 2) {
    			button = new JButton("Indiana Avenue");
    			button.setLocation(156, 40);
    		} else if (i == 3) {
    			button = new JButton("Illinois Avenue");
    			button.setLocation(190, 40);
    		} else if (i == 4) {
    			button = new JButton("B. & O. Railroad");
    			button.setLocation(224, 40);
    		} else if (i == 5) {
    			button = new JButton("Atlantic Avenue");
    			button.setLocation(258, 40);
    		} else if (i == 6) {
    			button = new JButton("Ventnor Avenue");
    			button.setLocation(292, 40);
    		} else if (i == 7) {
    			button = new JButton("Water Works");
    			button.setLocation(326, 40);
    		} else if (i == 8) {
    			button = new JButton("Marvin Gardens");
    			button.setLocation(360, 40);
    		} else if (i == 9) {
    			button = new JButton("Connecticut Avenue");
    			button.setLocation(88, 407);
    		} else if (i == 10) {
    			button = new JButton("Vermont Avenue");
    			button.setLocation(122, 407);
    		} else if (i == 11) {
    			button = new JButton("Oriental Avenue");
    			button.setLocation(190, 407);
    		} else if (i == 12) {
    			button = new JButton("Reading Railroad");
    			button.setLocation(224, 407);
    		} else if (i == 13) {
    			button = new JButton("Baltic Avenue");
    			button.setLocation(292, 407);
    		} else if (i == 14) {
    			button = new JButton("Mediterranean Avenue");
    			button.setLocation(360, 407);
    		}
    		button.setSize(36, 59);
    		button.setOpaque(false);
    		button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.addActionListener(this);
    		add(button);
    	}
    	for (int i = 1; i < 15; i++) {
    		//Left and Right properties
    		JButton button = new JButton("");
    		if (i == 1) {
    			button = new JButton("New York Avenue");
    			button.setLocation(30, 97);
    		} else if (i == 2) {
    			button = new JButton("Tennessee Avenue");
    			button.setLocation(30, 131);
    		} else if (i == 3) {
    			button = new JButton("St. James Place");
    			button.setLocation(30, 199);
    		} else if (i == 4) {
    			button = new JButton("Pennsylvania Railroad");
    			button.setLocation(30, 233);
    		} else if (i == 5) {
    			button = new JButton("Virginia Avenue");
    			button.setLocation(30, 267);
    		} else if (i == 6) {
    			button = new JButton("States Avenue");
    			button.setLocation(30, 301);
    		} else if (i == 7) {
    			button = new JButton("Electric Company");
    			button.setLocation(30, 335);
    		} else if (i == 8) {
    			button = new JButton("St. Charles Place");
    			button.setLocation(30, 369);
    		} else if (i == 9) {
    			button = new JButton("Pacific Avenue");
    			button.setLocation(396, 97);
    		} else if (i == 10) {
    			button = new JButton("North Carolina Avenue");
    			button.setLocation(396, 131);
    		} else if (i == 11) {
    			button = new JButton("Pennsylvania Avenue");
    			button.setLocation(396, 199);
    		} else if (i == 12) {
    			button = new JButton("Short Line");
    			button.setLocation(396, 233);
    		} else if (i == 13) {
    			button = new JButton("Park Place");
    			button.setLocation(396, 302);
    		} else if (i == 14) {
    			button = new JButton("Boardwalk");
    			button.setLocation(396, 370);
    		}
			button.setSize(59, 36);
    		button.setOpaque(false);
    		button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.addActionListener(this);
    		add(button);
    	}
    }

    /** Draws the property that was clicked on */
    private void clickDraw(Graphics2D g) {
        g.setColor(new Color(15, 122, 57));
        if (_property.position().equals("vert")) {
            g.fillRoundRect(_property.x(), _property.y(), 34, 57, 1, 1);
        } else {
            g.fillRoundRect(_property.x(), _property.y(), 57, 34, 1, 1);            
        }
    }

    /** Gets the property that was clicked */
    public Property property() {
        return _property;
    }


    /** Takes care of mortgage pop up buttons */
    @Override
    public void actionPerformed(ActionEvent e) {
        _paintClick = true;
        for (BoardNode curr = _game.getBoard().next(); 
            !(curr.piece().name().equals("Go")); curr = curr.next()) {
            if (curr.piece() instanceof Property) {
                if (curr.piece().name().equals(e.getActionCommand())) {
                    _property = (Property) curr.piece();
                }
            }
        }
        repaint();
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

















