
import java.awt.event.*;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameFrame extends JFrame {

    static Screen screen;

    public GameFrame() {
        setTitle("Horse Betting");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/assets/WhiteHorseLowRes.png")
        );

        setIconImage(icon.getImage());
        screen = new Screen();
        add(screen);

        KeyStroke keyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK
        );

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(keyStroke, "save");

        getRootPane().getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlayerData.save();
                } catch (Exception ex) {
                    // Intentionally ignored
                }
            }
        });
    }
}
