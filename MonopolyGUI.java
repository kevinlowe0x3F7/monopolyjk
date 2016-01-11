import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** The GUI for monopoly (Controller).
 *  @author Kevin Lowe
 */
public class MonopolyGUI {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;

    /** My frame. */
    private JFrame _frame;

    /** My main panel that is in _frame. */
    private JPanel _panel;

    /** My Monopoly board which gets placed in _panel. */
    private BoardPanel _board;

    /** My Player panel which gets placed inside _panel. */
    private JPanel _players;

    /** My Message field which gets placed inside _panel. */
    private JPanel _messages;

    /** Initializes the player panel with constraints C. */
    public void initializePlayers(GridBagConstraints c) {
    }

    /** Initializes the message box with constraints C. */
    public void initializeMessages(GridBagConstraints c) {
    }

    /** Initializes the buttons with constraints C. */
    public void initializeButtons(GridBagConstraints c) {
    }

    /** Initializes all of the buttons 
    /** Initializes the panels and buttons. */
    public MonopolyGUI(Monopoly game) {
        _game = game;

        _panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        _board = new BoardPanel(_game);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        _panel.add(_board, c);
        initializePlayers(c);
        initializeMessages(c);
        initializeButtons(c);

        _frame = new JFrame("Monopoly");
        _frame.add(_panel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(900, 700);
        _frame.setResizable(false);
        _frame.revalidate();
        _frame.setVisible(true);
    }
}
