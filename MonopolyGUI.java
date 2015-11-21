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
    /** My frame. */
    private JFrame _frame;

    /** My main panel that is in _frame. */
    private JPanel _panel;

    public MonopolyGUI() {
        _panel = new JPanel();
        _panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        ImageIcon image = new ImageIcon("board.jpg");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel boardPic = new JLabel(image);
        _panel.add(boardPic, c);
        _frame = new JFrame("Monopoly");
        _frame.add(_panel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(1000, 1000);
        _frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        MonopolyGUI m = new MonopolyGUI();
    }
}
