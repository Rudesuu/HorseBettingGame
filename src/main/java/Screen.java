
import java.awt.CardLayout;
import javax.swing.JPanel;

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

    String[][] getTestResults() {
        return new String[][]{
            {"1st", "Golden horse", "47.07s"},
            {"2nd", "Black horse", "53.87s"},
            {"3rd", "White horse", "62.34s"},
            {"4th", "Diamond horse", "DNF"}
        };
    }

    void showLeaderboard(String[][] results) {
        leaderboardPanel.updateResults(results);
        cardLayout.show(this, "Leaderboard");
    }

    void prepareRace() {
        racePanel.mapHorsesToLabels();
        racePanel.resetHorsePositions();
        racePanel.updateHorsePositions();
        showRace();
        racePanel.startMovement();
    }
}
