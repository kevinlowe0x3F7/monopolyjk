import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


/** The GUI for monopoly (Controller).
 *  @author Kevin Lowe
 */
public class MonopolyGUI implements ActionListener {
    /** The Monopoly game where I get the information from. */
    private Monopoly _game;

    /** My frame. */
    private JFrame _frame;

    /** My main panel that is in _frame. */
    private MainPanel _panel;

    /** Initializes the panels and buttons. */
    public MonopolyGUI(int players) {
        _game = new Monopoly(players, this);
        Player one = _game.players()[1];
        Player two = _game.players()[2];
        Player three = _game.players()[3];
        Player four = _game.players()[4];
        one.movePlayer(1);
        two.movePlayer(11);
        three.movePlayer(21);
        four.movePlayer(31);
        _panel = new MainPanel(_game, this);
        _panel.setBounds(0, 0, 800, 650);
        
        _frame = new JFrame("Monopoly");
        _frame.setLayout(null);
        _frame.add(_panel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(800, 650);
        _frame.setResizable(false);
        _frame.setVisible(true);
    }

    /** Returns my panel. */
    public MainPanel panel() {
        return _panel;
    }

    /** Takes care of all button actions. */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "New Game":
                _game = new Monopoly(_game.getNumPlayers());
                // Update status ---> New Game
                break;

            case "Quit":
                System.exit(0);
                break;

            case "About":
                //Pop up window and credits
                //aboutPopUp();
                buyPropertyPopUp("Mediterranean Avenue");
                break;

            case "Roll Dice":
                // The graphics of rolling the dice
                rollDice();
                break;

            case "Mortgage":
                // Pop up Window
                mortgagePopUp();
                break;

            case "Buy/Sell Houses":
                // Pop up Window for Buy/Sell Houses 
                break;

            case "Trade":
                // Pop up window for trading
                tradePopUp();
                break;

            case "Buy":
                _game.current().buyProperty(_game.current().location().piece());
                break;

            case "Don't Buy":
                //Exit the Jframe
                break;

            case "End Turn":
                break;
        }
    }

    /** Handles the GUI dice roll */
    private void rollDice() {
        Player current = _game.current();
        if (current.isJailed()) {
            jailedPopUp(current);
        }

        current.turn();
        _panel.board().repaint();

        String landedatrib = current.resolveLanding();
        if (landedatrib.equals("Buying/Auctioning Property")) {
            //buyPropertyPopUp(current.location().piece().name());
        }

        _panel.status().addLine(landedatrib);
    } 

    /** Handles the pop up for when the player is in Jail */
    private void jailedPopUp(Player current) {
        if (current.jailFree()) {
            Object[] possibleChoices = {"Pay $50", "Roll Dice", "Use Get out of Jail Free Card"}; 
         }
        Object[] possibleChoices = {"Pay $50", "Roll Dice"}; 

        String question = "You have " + current.jailedTurns() + " turns in Jail\n" 
            + "Choose one of the following\n";

        int selectedValue = (int) JOptionPane.showOptionDialog(null, question, "In Jail",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleChoices, null);

        // Pay $50
        if (selectedValue == 0) {
            current.loseMoney(50);
            current.inJail(false);
        // Roll Dice
        } else if (selectedValue == 1) {
            return;
        // Use Jail Free Card
        } else {
            if (current.jailFree()) {
                current.jailFree(false);
                current.inJail(false);
            } else {
                System.out.println("Player does not have a Jail Free Card");      
            }
        }
    }


    /** Handles the pop up for Buying Property */
    private void buyPropertyPopUp(String property) {
        //TODO Create the Frame 
        JFrame buyFrame = new JFrame("Buying Property");
        BuyPropertyPanel buyPanel = new BuyPropertyPanel(property);
        buyPanel.setBounds(0, 0, 400, 325);
        buyFrame.add(buyPanel);
        buyFrame.setLayout(null);
        buyFrame.setSize(400, 325);
        buyFrame.setResizable(false);
        buyFrame.setLocation(188, 200);
        buyFrame.setVisible(true);
    }

    private void auctioningPopUp() {
        //TODO
    }

    /** Handles the case when mortgage is pressed */
    private void mortgagePopUp() {
        //TODO
    }

    /** Handles the case when trade is pressed */
    private void tradePopUp() {
        //TODO
    }

    /** Case when about is pressed */
    private void aboutPopUp() {
        //TODO
    }

    /** Case when you upgrade property */
    private void houses() {
        //TODO
    }
}





