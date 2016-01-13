import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

/** Panel for the background image.
 *  @author Kevin Lowe
 */
public class BackgroundPanel extends JPanel {
    public BackgroundPanel() {
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawBackground(g);
    }

    /** Renders the wood.jpg background image and displays it. */
    public void drawBackground(Graphics g) {
        InputStream in = getClass().getResourceAsStream("wood.jpg");
        Image background = null;
        try {
            background = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error rendering background.");
        }
        g.drawImage(background, 0, 0, 800, 650, null);
    }
}
