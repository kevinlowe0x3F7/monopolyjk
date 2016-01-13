import javax.swing.*;
import java.awt.*;

/** Class to implement the status section of the GUI.
 *  @author Kevin Lowe
 */
public class StatusPanel extends JPanel {
    /** The status String of the game, updated every time something
     *  happens. Limited to 12 lines. */
    private String _status;
    /** Character limit for the status line. Any single status line that
     *  goes over this limit will need ot be drawn on another line. */
    private static final int CHAR_LIMIT = 33;
    /** Line limit for the status box. */
    private static final int LINE_LIMIT = 12;
    /** True if there are max lines on the status. */
    private boolean _maxLines;

    public StatusPanel() {
        super();
        _status = "Welcome to Monopoly!\n";
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintStatus((Graphics2D) g);
    }

    /** Add the status field. */
    public void paintStatus(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, 300, 245, 20, 20);
        Font font = new Font("Arial Black", Font.PLAIN, 14);
        g.setFont(font);
        int x = 10;
        int y = 20;
        for (String line : _status.split("\n")) {
            g.drawString(line, x, y);
            y += 20;
        }
    }

    /** Add the given line to the status method. */
    public void addLine(String line) {
        int lines = 1;
        String firstLine = "";
        String secondLine = "";
        if (line.length() > CHAR_LIMIT) {
            int lastSpace = line.lastIndexOf(' ', CHAR_LIMIT);
            firstLine = line.substring(0, lastSpace);
            secondLine = line.substring(lastSpace + 1);
            lines = 2;
        }
        if (lines == 1) {
            _status += (line + '\n');
        } else {
            _status += (firstLine + '\n');
            _status += (secondLine + '\n');
        }
        if (_maxLines) {
            deleteLine();
            if (lines == 2) {
                deleteLine();
            }
        }
        if (getLines() >= LINE_LIMIT) {
            _maxLines = true;
        }
    }

    /** Returns the number of lines in the status String. */
    public int getLines() {
        int count = 0;
        for (int i = 0; i < _status.length(); i++) {
            if (_status.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }
    
    /** Delete the first line of the status, indicated by the first
     *  new line character. */
    public void deleteLine() {
        int newLine = _status.indexOf('\n');
        if (newLine == -1 || newLine == _status.length() - 1) {
            return;
        } else {
            _status = _status.substring(newLine + 1);
        }
    }
}
