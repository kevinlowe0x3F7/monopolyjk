import javax.swing.*;
import java.awt.*;

/** Button panel representing the four player options.
 *  @author Kevin Lowe
 */
public class ButtonPanel extends JPanel {
    private Monopoly _game;
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
        JButton roll = new JButton("Roll Dice");
        roll.setLocation(20, 50);
        roll.setSize(130, 70);
        roll.addActionListener(_gui);
        add(roll);
        JButton mortgage = new JButton("Mortgage");
        mortgage.setLocation(160, 50);
        mortgage.setSize(130, 70);
        mortgage.addActionListener(_gui);
        add(mortgage);
        JButton houses = new JButton("Buy/Sell Houses");
        houses.setLocation(20, 140);
        houses.setSize(130, 70);
        houses.addActionListener(_gui);
        add(houses);
        JButton trade = new JButton("Trade");
        trade.setLocation(160, 140);
        trade.setSize(130, 70);
        trade.addActionListener(_gui);
        add(trade);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(275, 250);
        return d;
    }
}
