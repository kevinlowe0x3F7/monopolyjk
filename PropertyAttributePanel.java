import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

/** Class to implement the property attribute pop up of the GUI, containing 
 *  description of the property and button choices
 *  @author Joseph
 *
 *  Credits: http://monopoly.wikia.com/wiki/Property
 */
public class PropertyAttributePanel extends JPanel {


	/** The property attritube image */
	private Image _property;
    
    /** The Monopoly GUI Board Panel */
    private BoardPanel _boardPanel;

    public PropertyAttributePanel(String property, BoardPanel boardPanel) {
    	super();
        _boardPanel = boardPanel;
    	InputStream in = getClass().getResourceAsStream(
    		"resources/properties_images/" + property + ".jpg");
        try {
            _property = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("error loading board image.");
            System.exit(1);
        }
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawComponent((Graphics2D) g);
    }

    private void drawComponent(Graphics2D g) {
    	// Draws the Property Attribute Image
    	g.drawImage(_property, 0, 0, 350, 245, null);
        JButton exit = new JButton("Exit");
        exit.setLocation(138, 250);
        exit.setSize(75, 50);
        exit.addActionListener(_boardPanel);
        add(exit);
    }
}
