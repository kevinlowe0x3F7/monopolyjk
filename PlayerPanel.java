import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.*;

/** Class for the player panel which indicates the player ID, money,
 *  and location for each person.
 *  @author Kevin Lowe
 */
public class PlayerPanel extends JPanel {
    /** The Monopoly game where I get information from. */
    private Monopoly _game;

    public PlayerPanel(Monopoly game) {
        super(new GridBagLayout());
        _game = game;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        displayStats();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(400, 100);
        return d;
    }

    /** Displays all of the stats for each of the players in the
     *  Monopoly game. */
    public void displayStats() {
        Player[] players = _game.players();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        for (int i = 1; i < players.length; i++) {
            JTextArea info = new JTextArea(3, 75);
            info.setLineWrap(true);
            Font font = new Font("Arial Black", Font.PLAIN, 13);
            if (_game.current().getID() == i) {
                font = new Font("Arial Black", Font.ITALIC, 13);
            }
            if (i == 1) {
                c.insets = new Insets(0, 0, 0, 10);
            } else if (i == players.length - 1) {
                c.insets = new Insets(0, 10, 0, 0);
            } else {
                c.insets = new Insets(0, 10, 0, 10);
            }
            info.setFont(font);
            String player = "Player " + i + '\n';
            int length = player.length();
            info.append("Player " + i + '\n');
            Player next = players[i];
            if (next == null) {
                info.append("Bankrupt" + '\n');
            } else {
                String moneyLoc = "$" + next.money() + '\n' +
                    next.location().piece().name();
                length += moneyLoc.length();
                info.append(moneyLoc);
                if (_game.current().getID() == i) {
                    Highlighter highlighter = info.getHighlighter();
                    HighlightPainter painter =
                        new DefaultHighlighter.DefaultHighlightPainter(
                                Color.RED);
                    try {
                        highlighter.addHighlight(0, length, painter);
                    } catch (BadLocationException b) {
                    }
                }
            }
            add(info, c);
            c.gridx += 1;
        }
        /*
        JTextField info1 = new JTextField("Italics indicate current player.");
        info1.setHorizontalAlignment(JTextField.CENTER);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = players.length - 1;
        add(info1, c);
        */
    }

    public static void main(String[] args) {
        Monopoly m = new Monopoly(4);
        PlayerPanel p = new PlayerPanel(m);
        JFrame f = new JFrame("Test");
        f.add(p);
        f.setSize(1000, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        System.out.println("exit visible");
    }
}
