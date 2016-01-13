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

    /** My background panel. */
    BackgroundPanel _background;
    /** My board panel. */
    BoardPanel _board;
    /** My right-side buttons panel. */
    ButtonPanel _buttons;
    /** My player panel. */
    PlayerPanel _players;
    /** My top buttons panel. */
    TopButtonPanel _topButtons;
    /** My status panel. */
    StatusPanel _status;

    /** Number of repaints that have occured. */
    private int _paintCount;

    /** Initializes all the parts. */
    public MainPanel(Monopoly game, MonopolyGUI gui) {
        _game = game;
        _gui = gui;
        setLayout(null);

        /*
        _background = new BackgroundPanel();
        _background.setBounds(0, 0, 800, 650);
        add(_background);
        */

        _board = new BoardPanel(_game);
        _board.setBounds(0, 20, 465, 465);
        add(_board);

        _buttons = new ButtonPanel(_game, _gui);
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

        setOpaque(true);
        setBounds(0, 0, 800, 650);
        _paintCount = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Paint count: " + _paintCount);
        _paintCount++;
        _board.repaint();
        _players.repaint();
        _status.repaint();
    }

    public void addLine(String line) {
        _status.addLine(line);
    }
}
