
import java.awt.CardLayout;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private final CardLayout cardLayout;

    public Screen() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        StartPanel startPanel = new StartPanel();
        RaceSetupPanel raceSetupPanel = new RaceSetupPanel();
        RacePanel racePanel = new RacePanel();
        add(startPanel, "Start");
        add(raceSetupPanel, "Setup");
        add(racePanel, "Race");
        //Make sure .show works correctly
        cardLayout.show(this, "Race");
        //Make sure .show works correctly
    }
}
