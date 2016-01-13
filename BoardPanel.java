import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

/** Class for the board panel which contains the image and any markers to
 *  help indicate where players are and who owns which property. */
public class BoardPanel extends JPanel {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;

    public BoardPanel(Monopoly game) {
        super();
        _game = game;
        setBackground(new Color(0, 102, 0));
        this.setOpaque(false);
        this.setLayout(null);
    }

    /** Draws the board itself. */
    private void drawBoard(Graphics2D g) {
        InputStream in = getClass().getResourceAsStream("board.jpg");
        Image board;
        try {
            board = ImageIO.read(in);
        } catch (IOException e) {
            board = null;
        }
        g.drawImage(board, 30, 30, 425, 425, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawBoard((Graphics2D) g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(465, 465);
        return d;
    }
}
