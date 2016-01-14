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
public class MonopolyGUI implements ActionListener {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;

    /** My frame. */
    private JFrame _frame;

    /** My main panel that is in _frame. */
    private MainPanel _panel;

    /** Initializes the panels and buttons. */
    public MonopolyGUI(Monopoly game) {
        _game = game;
        _game.players()[1].movePlayer(32);
        _panel = new MainPanel(_game, this);
        _panel.setBounds(0, 0, 800, 650);
        
        _frame = new JFrame("Monopoly");
        _frame.setLayout(null);
        _frame.add(_panel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(800, 650);
        _frame.setResizable(false);
        _frame.setVisible(true);
    }

    /** Takes care of actions. */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        _panel.status().addLine(command);
        _panel.status().repaint();

        if (command.equals("New Game")) {
            _game = new Monopoly(_game.getNumPlayers());
        } else if (command.equals("Quit")) {
            System.exit(0);
        } else if (command.equals("Roll Dice")) {
            _game.nextPlayer();
            _panel.players().repaint();
        }
    }
}
