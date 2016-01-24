import javax.swing.*;
import java.awt.*;

/** Panel for the top buttons of the GUI.
 *  @author Kevin Lowe
 */
public class TopButtonPanel extends JPanel {
    /** The GUI that listens to actions. */
    private MonopolyGUI _gui;

    public TopButtonPanel(MonopolyGUI gui) {
        super();
        _gui = gui;
        this.setOpaque(false);
        this.setLayout(null);
        initButtons();
    }

    /** Add the top section of buttons. */
    public void initButtons() {
        JButton newGame = new JButton("New Game");
        newGame.setLocation(15, 15);
        newGame.setSize(120, 20);
        newGame.addActionListener(_gui);
        add(newGame);

        JButton quit = new JButton("Quit");
        quit.setLocation(330, 15);
        quit.setSize(120, 20);
        quit.addActionListener(_gui);
        add(quit);

        JButton about = new JButton("About");
        about.setLocation(660, 15);
        about.setSize(120, 20);
        about.addActionListener(_gui);
        add(about);
    }
}
