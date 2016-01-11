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
    //     setSize(485, 485);
        /*
        ImageIcon image = new ImageIcon("board.jpg");
        JLabel boardPic = new JLabel(image);
        add(boardPic);
        */
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
        g.drawImage(board, 30, 30, 500, 500, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawBoard((Graphics2D) g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(560, 560);
        return d;
    }

    public static void main(String[] args) {
        Monopoly m = new Monopoly(2);
        BoardPanel b = new BoardPanel(m);
        JFrame f = new JFrame("test");
        f.add(b);
        f.setVisible(true);
    }

}
