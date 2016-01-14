import javax.swing.*;
import java.awt.*;

/** Button panel representing the four player options.
 *  @author Kevin Lowe
 */
public class ButtonPanel extends JPanel {
    /** Monopoly game that I get information from. */
    private Monopoly _game;
    /** GUI that listens to actions. */
    private MonopolyGUI _gui;
    
    public ButtonPanel(Monopoly game, MonopolyGUI gui) {
        super();
        _game = game;
        _gui = gui;
        initButtons();
        this.setOpaque(false);
        this.setLayout(null);
    }

    /** Set up buttons are proper locations. */
    public void initButtons() {
        // TODO switch buttons based on roll or end turn
        // switch buttons if surrendering
        int width = 140;
        int height = 70;
        JButton roll = new JButton("Roll Dice");
        roll.setLocation(20, 40);
        roll.setSize(width, height);
        roll.addActionListener(_gui);
        add(roll);
        JButton mortgage = new JButton("Mortgage");
        mortgage.setLocation(170, 40);
        mortgage.setSize(width, height);
        mortgage.addActionListener(_gui);
        add(mortgage);
        JButton houses = new JButton("Buy/Sell Houses");
        houses.setLocation(20, 120);
        houses.setSize(width, height);
        houses.addActionListener(_gui);
        add(houses);
        JButton trade = new JButton("Trade");
        trade.setLocation(170, 120);
        trade.setSize(width, height);
        trade.addActionListener(_gui);
        add(trade);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(275, 250);
        return d;
    }
}
