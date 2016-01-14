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
        super();
        _game = game;
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        displayStats((Graphics2D) g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(800, 130);
        return d;
    }

    /** Displays the player stats, indicating ID, money amount,
     *  and location. */
    public void displayStats(Graphics2D g) {
        int startX = 15;
        Player[] players = _game.players();
        for (int i = 1; i < players.length; i++) {
            g.setColor(Monopoly.COLORS[i]);
            int yCoord = 40;
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
}
