import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.IOException;

/** Class to implement the buy property pop up of the GUI, containing 
 *  description of the property and button choices
 *  @author Joseph
 *
 *  Credits: http://monopoly.wikia.com/wiki/Property
 */
public class BuyPropertyPanel extends JPanel {
	/** GUI that listens to actions */
	private MonopolyGUI _gui;

	/** The property attritube image */
	private Image _property;

    public BuyPropertyPanel(String property, MonopolyGUI gui) {
    	//TODO
    	super();
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
        _gui = gui;
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawComponent((Graphics2D) g);
    }

    private void drawComponent(Graphics2D g) {
    	// Draws the Property Attribute Image
    	g.drawImage(_property, 0, 0, 350, 245, null);
    	System.out.println("Property attributes drawn");
    	// Adding Buttons
    	JButton buy = new JButton("Buy");
    	buy.setLocation(95, 250);
    	buy.setSize(75, 50);
    	buy.addActionListener(_gui);
    	add(buy);
    	JButton no = new JButton("No");
    	no.setLocation(180, 250);
    	no.setSize(75, 50);
    	no.addActionListener(_gui);
    	add(no);
    }
}












