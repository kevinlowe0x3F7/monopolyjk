import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

/** Class to implement the main panel of the GUI, containing all parts
 *  of the GUI:board, player stats, buttons, message box (View).
 *  @author Kevin Lowe
 *
 *  Credits: Board: http://bradfrost.com/blog/post/monopoly-photoshop-template/
 *  Wood: http://thaidigitalpublishing.com/wp-content/uploads/2012/01/6-Wood-Pattern-Background-Thumb01.jpg
 */
public class MainPanel extends JPanel {
    /** The Monopoly game that I get information from. */
    private Monopoly _game;
    /** The GUI that this display is in. */
    private MonopolyGUI _gui;

    /** My board panel. */
    private BoardPanel _board;
    /** My right-side buttons panel. */
    private ButtonPanel _buttons;
    /** My player panel. */
    private PlayerPanel _players;
    /** My top buttons panel. */
    private TopButtonPanel _topButtons;
    /** My status panel. */
    private StatusPanel _status;

    /** Background image. */
    private Image _background;

    /** Initializes all the parts. */
    public MainPanel(Monopoly game, MonopolyGUI gui) {
        _game = game;
        _gui = gui;
        setLayout(null);

        InputStream in = getClass().getResourceAsStream(
                "resources/wood.jpg");
        try {
            _background = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("error loading background image");
            System.exit(1);
        }

        _board = new BoardPanel(_game);
        _board.setBounds(0, 20, 465, 465);
        add(_board);

        _buttons = new ButtonPanel(_gui);
        _buttons.setBounds(465, 20, 350, 250);
        add(_buttons);

        _players = new PlayerPanel(_game);
        _players.setBounds(0, 485, 800, 130);
        add(_players);

        _topButtons = new TopButtonPanel(_gui);
        _topButtons.setBounds(0, 0, 800, 35);
        add(_topButtons);

        _status = new StatusPanel();
        _status.setBounds(485, 230, 350, 250);
        add(_status);

        setBackground(new Color(255, 217, 156));
        setOpaque(true);
        setBounds(0, 0, 800, 650);
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("draw background");
        g.drawImage(_background, 0, 0, 800, 650, null);
    }

    /** Returns the board panel. */
    public BoardPanel board() {
        return _board;
    }

    /** Returns the right-side buttons panel. */
    public ButtonPanel buttons() {
        return _buttons;
    }

    /** Returns the player panel. */
    public PlayerPanel players() {
        return _players;
    }

    /** Returns the top buttons panel. */
    public TopButtonPanel topButtons() {
        return _topButtons;
    }

    /** Return the status panel. */
    public StatusPanel status() {
        return _status;
    }
}
