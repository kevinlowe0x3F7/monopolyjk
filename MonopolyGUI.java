import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** The GUI for monopoly (Controller).
 *  @author Kevin Lowe
 */
// TODO Update status method, all game implementation (lol)
public class MonopolyGUI implements ActionListener {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;

    /** My frame. */
    private JFrame _frame;

    /** My main panel that is in _frame. */
    private JPanel _panel;

    /** My Monopoly board which gets placed in _panel. */
    private BoardPanel _board;

    /** My Player panel which gets placed inside _panel. */
    private PlayerPanel _players;

    /** My Message field which gets placed inside _panel. */
    private JTextArea _messages;

    /** The status String of the game, updated every time something
     *  happens. Limited to 10 lines. */
    private String _status;

    /** Initializes the board panel with constraints C. */
    public void initializeBoard(GridBagConstraints c) {
        _board = new BoardPanel(_game);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridheight = 6;
        c.gridy = 0;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        _panel.add(_board, c);
    }

    /** Initializes the player panel with constraints C. */
    public void initializePlayers(GridBagConstraints c) {
        _players = new PlayerPanel(_game);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridheight = 3;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        _panel.add(_players, c);
    }

    /** Initializes the sidebar with constraints C, which includes
     *  the message box and all of the buttons. */
    public void initializeSidebar(GridBagConstraints c) {
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 50;
        c.ipady = 30;
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(this);
        _panel.add(newGame, c);
        c.gridy = 1;
        JButton quit = new JButton("Quit");
        quit.addActionListener(this);
        _panel.add(quit, c);
        c.gridy = 2;
        JButton about = new JButton("About");
        about.addActionListener(this);
        _panel.add(about, c);
        c.gridy = 3;

        c.ipadx = 0;
        _status = "Welcome to Monopoly!" + '\n';
        _messages = new JTextArea(_status, 10, 25);
        _messages.setLineWrap(true);
        _messages.setEditable(false);
        _panel.add(_messages, c);

        c.gridy = 4;
        JButton roll = new JButton("Roll Dice");
        c.ipadx = 80;
        roll.addActionListener(this);
        _panel.add(roll, c);
        c.gridy = 5;
        JButton mortgage = new JButton("Mortgage");
        mortgage.addActionListener(this);
        _panel.add(mortgage, c);
        c.gridy = 6;
        JButton houses = new JButton("Buy/Sell Houses");
        houses.addActionListener(this);
        _panel.add(houses, c);
        c.gridy = 7;
        JButton trade = new JButton("Trade");
        trade.addActionListener(this);
        _panel.add(trade, c);
    }

    /** Initializes the panels and buttons. */
    public MonopolyGUI(Monopoly game) {
        _game = game;

        _panel = new JPanel(new GridBagLayout());
        _panel.setBackground(new Color(0, 102, 0));
        GridBagConstraints c = new GridBagConstraints();
        
        initializeBoard(new GridBagConstraints());
        initializePlayers(new GridBagConstraints());
        initializeSidebar(new GridBagConstraints());

        _frame = new JFrame("Monopoly");
        _frame.setContentPane(_panel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(900, 650);
        _frame.setResizable(false);
        _frame.setVisible(true);
        ((JComponent) _frame.getContentPane()).revalidate();
        _frame.repaint();
    }

    /** Takes care of actions. */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
