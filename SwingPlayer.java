import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingPlayer extends SwingWorker<Void, String> {

    private Player _player;
    public SwingPlayer(Player player) {
        _player = player;
    }
    @Override
    protected Void doInBackground() throws Exception {
        while (!_player.location().piece().name().equals("Go")) {
            _player.setLocation(_player.location().next());
            publish();
            Thread.sleep(250);
        }
        return null;
    }

    protected void process(List<String> chunks) {
        _player.game().gui().panel().board().repaint();
    }
}
