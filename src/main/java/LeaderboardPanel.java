
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LeaderboardPanel extends JPanel {

    private DefaultTableModel tableModel;
    private final String[] columns = {"Position", "Horse", "Finish time"};

    public LeaderboardPanel(Screen screen) {
        setLayout(new BorderLayout(0, 15));
        setBackground(new java.awt.Color(232, 232, 228));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel leaderboardLabel = new JLabel("Leaderboard", JLabel.CENTER);
        leaderboardLabel.setFont(new Font("Arial", Font.BOLD, 35));

        String[][] results = {
            {"1st", "Golden horse", "47.07s"},
            {"2nd", "Black horse", "53.87s"},
            {"3rd", "White horse", "62.34s"},
            {"4th", "Diamond horse", "DNF"}
        };

        tableModel = new DefaultTableModel(results, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable resultsTable = new JTable(tableModel);
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

        nextButton.addActionListener(e -> screen.showSetup());
    }

    public void updateResults(String[][] results) {
        tableModel.setDataVector(results, columns);
    }

}
