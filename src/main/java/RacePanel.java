
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RacePanel extends JPanel {

    public RacePanel() {
        setLayout(new BorderLayout());

        JPanel trackPanel = new JPanel(new GridLayout(4, 1, 0, 15));

        String[] lanes = {"White horse", "Black horse", "Golden horse", "Diamond horse"};

        for (String lane : lanes) {

            JPanel horseLane = new JPanel(new BorderLayout());
            horseLane.setBackground(new Color(196, 122, 58));

            ImageIcon horseIcon = new ImageIcon(
                    getClass().getResource("/assets/WhiteHorseLowRes.png")
            );

            JLabel horseLabel = new JLabel(horseIcon);
            horseLabel.setHorizontalAlignment(JLabel.CENTER);

            horseLane.add(horseLabel, BorderLayout.WEST);
            trackPanel.add(horseLane);
        }

        JPanel finishPanel = new JPanel(new GridLayout(16, 3));
        finishPanel.setPreferredSize(new Dimension(80, 0));

        for (int i = 0; i < 48; i++) {
            JPanel flag = new JPanel();

            if (i % 2 == 1) {
                flag.setBackground(Color.WHITE);
            } else {
                flag.setBackground(Color.BLACK);
            }

            finishPanel.add(flag);
        }

        add(trackPanel, BorderLayout.CENTER);
        add(finishPanel, BorderLayout.EAST);
    }
}
