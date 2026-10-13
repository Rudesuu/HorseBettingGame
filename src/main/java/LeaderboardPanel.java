
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel() {
        setLayout(new BorderLayout(0, 15));
        setBackground(new java.awt.Color(232, 232, 228));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel leaderboardLabel = new JLabel("Leaderboard", JLabel.CENTER);
        leaderboardLabel.setFont(new Font("Arial", Font.BOLD, 35));

        String[] columns = {"Position", "Horse", "Finish time"};

        String[][] results = {
            {"1st", "Golden horse", "47.07s"},
            {"2nd", "Black horse", "53.87s"},
            {"3rd", "White horse", "62.34s"},
            {"4th", "Diamond horse", "DNF"}
        };

        JTable resultsTable = new JTable(results, columns);
        resultsTable.setRowHeight(60);
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 40));
        resultsTable.getTableHeader().setPreferredSize(
                new java.awt.Dimension(0, 45)
        );
        resultsTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 40)
        );
        resultsTable.setBackground(new java.awt.Color(232, 232, 228));

        JLabel netEarningsLabel = new JLabel("Net earnings: €225");
        netEarningsLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel labelButtonPanel = new JPanel(new BorderLayout());
        labelButtonPanel.setBackground(new java.awt.Color(232, 232, 228));
        labelButtonPanel.add(netEarningsLabel, BorderLayout.CENTER);
        labelButtonPanel.add(nextButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.getViewport().setBackground(new Color(232, 232, 228));

        add(leaderboardLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(labelButtonPanel, BorderLayout.SOUTH);
    }
}

