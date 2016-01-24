import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /** Pop up JFrame */
    private JFrame _popUpFrame;

    /** Mortgage Property Pop Up Panel */
    private MortgagePropertyPanel _mortPanel;

    /** Initializes the panels and buttons. */
    public MonopolyGUI(int players) {
        _game = new Monopoly(players, this);
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

    /** Takes care of all main board button actions. */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New Game":
                _game = new Monopoly(_game.getNumPlayers(), this);
                resetGames(_game);
                _panel.status().addLine("New Game Started");
                _panel.status().repaint();
                _panel.board().repaint();
                _panel.players().repaint();
                _panel.buttons().roll().setText("Roll Dice");
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
                _panel.buttons().roll().setEnabled(false);
                if (_game.current().isJailed()) {
                    rollDiceJail();
                } else {
                    rollDice();
                }
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
                _game.current().buyProperty((Property)_game.current().location().piece());
                _panel.status().addLine("Player "+ _game.current().getID() + " bought " 
                    + _game.current().location().piece().name());
                _panel.status().repaint();
                _panel.board().repaint();
                _panel.players().repaint();
                _popUpFrame.dispose();
                break;

            case "No":
                _popUpFrame.dispose();
                break;

            case "Mortgage/Unmortgage Property":
                if (!(_mortPanel.property().mortgageToggle(_game.current()))) {
                    _panel.status().addLine("Not able to Mortgage/Unmortgage");
                    _panel.status().repaint();
                } else {
                    _panel.status().addLine("Mortgaged/Unmortgaged " + _mortPanel.property().name());
                    _panel.status().repaint();
                    _panel.board().repaint();
                }
                _popUpFrame.dispose();
                break;

            case "Exit":
                _popUpFrame.dispose();
                break;

            case "End Turn":
                endTurn();
                break;
        }
    }

    /** Reset all _game variables for the different panels with
     *  the new game. */
    public void resetGames(Monopoly newGame) {
        _panel.setGame(newGame);
        _panel.players().setGame(newGame);
        _panel.board().setGame(newGame);
    }

    /** Handles the GUI dice roll for a normal player's turn. */
    private void rollDice() {
        SwingWorker<Void, Void> mover = new SwingWorker<Void, Void>() {
            private boolean wasSentToJail = false;
            @Override
            protected Void doInBackground() throws Exception {
                Player current = _game.current();
                int[] rolls = current.rolls();
                rolls[0] = current.rollDice();
                String line = "Player " + current.getID() + " rolled a ";
                _panel.status().addLine(line + rolls[0]);
                publish();
                Thread.sleep(500);
                rolls[1] = current.rollDice();
                _panel.status().addLine(line + rolls[1]);
                publish();

                if (rolls[0] == rolls[1]) {
                    current.setDoubles(current.doubleTurns() + 1);
                    if (current.doubleTurns() == 3) {
                        String line1 = "3 doubles, Player " +
                            current.getID() + " has been sent to jail";
                        current.inJail(true);
                        current.jumpPlayer("Jail");
                        _game.nextPlayer();
                        wasSentToJail = true;
                        String line2 = "Player " + _game.current().getID()
                            + "'s turn";
                        _panel.status().addLine(line1);
                        _panel.status().addLine(line2);
                    } else {
                        current.movePlayer(current.getLastRoll());
                    }
                } else {
                    current.movePlayer(current.getLastRoll());
                    current.setDoubles(0);
                }
                if ((current.location().piece().name().equals(
                    "Jail") && (current.isJailed()))) {
                    wasSentToJail = true;
                    }
                return null;
            }

            protected void process(List<Void> chunks) {
                _panel.status().repaint();
                _panel.board().repaint();
            }

            protected void done() {
                _panel.status().repaint();
                _panel.board().repaint();
                _panel.players().repaint();
                if (!wasSentToJail) {
                    Player current = _game.current();
                    int[] rolls = current.rolls();
                    if (rolls[0] != rolls[1]) {
                        _panel.buttons().roll().setText("End Turn");
                    }
                }
                _panel.buttons().roll().setEnabled(true);
            }
        };
        mover.execute();
    } 

    /** Handles the GUI dice roll for when the player is in jail. */
    private void rollDiceJail() {
        SwingWorker<Void, Void> mover = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Player current = _game.current();
                String answer = jailedPopUp(current);
                if (answer.equals("Pay $50")) {
                    current.loseMoney(50);
                    publish();
                    current.inJail(false);
                    current.setDoubles(0);
                    rollDice();
                } else if (answer.equals("Roll Dice")) {
                    int[] rolls = current.rolls();
                    rolls[0] = current.rollDice();
                    String line = "Player " + current.getID() +
                        " rolled a ";
                    _panel.status().addLine(line + rolls[0]);
                    publish();
                    Thread.sleep(500);
                    rolls[1] = current.rollDice();
                    _panel.status().addLine(line + rolls[1]);
                    publish();

                    if (rolls[0] == rolls[1]) {
                        String line1 = "Doubles! Player " +
                            current.getID() + " is free.";
                        _panel.status().addLine(line1);
                        current.inJail(false);
                        current.setDoubles(0);
                        current.movePlayer(current.getLastRoll());
                    } else {
                        current.setTurns(current.jailedTurns() - 1);
                        if (current.jailedTurns() == 0) {
                            current.loseMoney(50);
                            publish();
                            current.inJail(false);
                            current.movePlayer(current.getLastRoll());
                        }
                    }
                    _panel.buttons().roll().setText("End Turn");
                    _panel.buttons().roll().setEnabled(true);
                } else {
                   current.jailFree(false);
                   current.inJail(false);
                   rollDice();
                }
                return null;
            }

            protected void process(List<Void> chunks) {
                _panel.status().repaint();
                _panel.board().repaint();
                _panel.players().repaint();
            }
        };
        mover.execute();
    }

    /** Handles the pop up for when the player is in Jail */
    private String jailedPopUp(Player current) {
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
            return "Pay $50";
        // Roll Dice
        } else if (selectedValue == 1) {
            return "Roll Dice";
        // Use Jail Free Card
        } else {
            return "Jail Free";
        }
    }


    /** Handles the pop up for Buying Property */
    public void buyPropertyPopUp(String property) {
        //TODO Create the Frame 
        _popUpFrame = new JFrame("Buying Property");
        BuyPropertyPanel buyPanel = new BuyPropertyPanel(property, this);
        buyPanel.setBounds(0, 0, 350, 325);
        _popUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _popUpFrame.add(buyPanel);
        _popUpFrame.setLayout(null);
        _popUpFrame.setSize(350, 325);
        _popUpFrame.setResizable(false);
        _popUpFrame.setLocation(213, 200);
        _popUpFrame.setVisible(true);
    }

    private void auctioningPopUp() {
        //TODO Optional
    }

    /** Handles the case when mortgage is pressed */
    private void mortgagePopUp() {
        //TODO
        _popUpFrame = new JFrame("Mortgaging Property");
        _mortPanel = new MortgagePropertyPanel(this, _game);
        _popUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _mortPanel.setBounds(0, 0, 800, 650);
        _popUpFrame.add(_mortPanel);
        _popUpFrame.setLayout(null);
        _popUpFrame.setSize(800, 650);
        _popUpFrame.setResizable(false);
        _popUpFrame.setVisible(true);
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
        _popUpFrame = new JFrame("Upgrading/Selling Houses");
        HousePanel housePanel = new HousePanel(this, _game);
        housePanel.setBounds(0, 0, 800, 650);
        _popUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _popUpFrame.add(housePanel);
        _popUpFrame.setLayout(null);
        _popUpFrame.setSize(800, 650);
        _popUpFrame.setResizable(false);
        _popUpFrame.setVisible(true);
    }

    /** Handles the end turn button. */
    private void endTurn() {
        Player before = _game.current();
        _game.nextPlayer();
        Player after = _game.current();
        _panel.status().addLine("Player " + before.getID() +
                " has ended their turn.");
        _panel.status().addLine("Player " + after.getID() + "'s turn.");
        _panel.status().repaint();
        _panel.players().repaint();
        _panel.board().repaint();
        _panel.buttons().roll().setText("Roll Dice");
    }
}
