import javax.swing.*;
import java.awt.*;

/** Button panel representing the four player options.
 *  @author Kevin Lowe
 */
public class ButtonPanel extends JPanel {
    /** GUI that listens to actions. */
    private MonopolyGUI _gui;
    /** Roll Dice/End Turn button. */
    private JButton _roll;
    /** Mortgage button. */
    private JButton _mortgage;
    /** Houses button. */
    private JButton _houses;
    /** Trade button. */
    private JButton _trade;
    
    public ButtonPanel(MonopolyGUI gui) {
        super();
        _gui = gui;
        initButtons();
        this.setOpaque(false);
        this.setLayout(null);
    }

    /** Set up buttons are proper locations. */
    public void initButtons() {
        int width = 140;
        int height = 70;
        _roll = new JButton("Roll Dice");
        _roll.setLocation(20, 40);
        _roll.setSize(width, height);
        _roll.addActionListener(_gui);
        add(_roll);
        _mortgage = new JButton("Mortgage");
        _mortgage.setLocation(170, 40);
        _mortgage.setSize(width, height);
        _mortgage.addActionListener(_gui);
        add(_mortgage);
        _houses = new JButton("Buy/Sell Houses");
        _houses.setLocation(20, 120);
        _houses.setSize(width, height);
        _houses.addActionListener(_gui);
        add(_houses);
        _trade = new JButton("Trade");
        _trade.setLocation(170, 120);
        _trade.setSize(width, height);
        _trade.addActionListener(_gui);
        add(_trade);
    }

    /** Returns the Roll Dice/End Turn button. */
    public JButton roll() {
        return _roll;
    }

    /** Returns the mortgage button. */
    public JButton mortgage() {
        return _mortgage;
    }

    /** Returns the houses button. */
    public JButton houses() {
        return _houses;
    }

    /** Returns the trade button. */
    public JButton trade() {
        return _trade;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(275, 250);
        return d;
    }
}
