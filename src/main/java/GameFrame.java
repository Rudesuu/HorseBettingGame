
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
    }
}
