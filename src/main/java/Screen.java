
import java.awt.CardLayout;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private final CardLayout cardLayout;

    public Screen() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        StartPanel startPanel = new StartPanel();
        add(startPanel, "Start");
        cardLayout.show(this, "Start");
    }
}
