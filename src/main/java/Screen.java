
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screen extends JPanel {

    private final CardLayout cardLayout;
    private final LeaderboardPanel leaderboardPanel;
    private final RacePanel racePanel;
    private final RaceSetupPanel raceSetupPanel;

    public Screen() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        StartPanel startPanel = new StartPanel(this);
        raceSetupPanel = new RaceSetupPanel(this);
        racePanel = new RacePanel(this);
        leaderboardPanel = new LeaderboardPanel(this);
        add(startPanel, "Start");
        add(raceSetupPanel, "Setup");
        add(racePanel, "Race");
        add(leaderboardPanel, "Leaderboard");
    }

    void showSetup() {
        cardLayout.show(this, "Setup");
        raceSetupPanel.refreshBalance();
    }

    void showRace() {
        cardLayout.show(this, "Race");
    }

    void showLeaderboard(String[][] results, int earnings) {
        SwingUtilities.invokeLater(() -> {
            leaderboardPanel.updateResults(results);
            leaderboardPanel.updateNetEarnings(earnings);
            cardLayout.show(this, "Leaderboard");
        });
    }

    void prepareRace() {
        racePanel.mapHorsesToLabels();
        racePanel.resetHorsePositions();
        racePanel.updateHorsePositions();
        showRace();
        racePanel.startMovement();
    }
}
