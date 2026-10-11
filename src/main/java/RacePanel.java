
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RacePanel extends JPanel {

    public RacePanel() {
        setLayout(new BorderLayout());

        JPanel trackPanel = new JPanel(new GridLayout(4, 1, 0, 15));

        String[] lanes = {"White horse", "Black horse", "Golden horse", "Diamond horse"};

        for (String lane : lanes) {

            JPanel horseLane = new JPanel(null);
            horseLane.setBackground(new Color(196, 122, 58));

            ImageIcon horseIcon = new ImageIcon(
                    getClass().getResource("/assets/WhiteHorseLowRes.png")
            );

            JLabel horseLabel = new JLabel(horseIcon);
            horseLabel.setSize(horseLabel.getPreferredSize());

            horseLane.add(horseLabel);
            trackPanel.add(horseLane);

            horseLane.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                    int y = (horseLane.getHeight() - horseLabel.getHeight()) / 2;
                    horseLabel.setLocation(horseLabel.getX(), y);
                }
            });
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

        final ImageIcon carrot = new ImageIcon(
                getClass().getResource("/assets/WhiteHorseLowRes.png")
        );
        final ImageIcon waterHose = new ImageIcon(
                getClass().getResource("/assets/WhiteHorseLowRes.png")
        );

        JButton carrotButton = new JButton(carrot);
        carrotButton.setHorizontalAlignment(JButton.CENTER);
        carrotButton.setText("€100");
        carrotButton.setHorizontalTextPosition(JButton.CENTER);
        carrotButton.setVerticalTextPosition(JButton.BOTTOM);
        carrotButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton waterHoseButton = new JButton(waterHose);
        waterHoseButton.setHorizontalAlignment(JButton.CENTER);
        waterHoseButton.setText("€100");
        waterHoseButton.setHorizontalTextPosition(JButton.CENTER);
        waterHoseButton.setVerticalTextPosition(JButton.BOTTOM);
        waterHoseButton.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(carrotButton, BorderLayout.EAST);
        buttonPanel.add(waterHoseButton, BorderLayout.WEST);
        
        JLabel balanceLabel = BalanceLabel.create();
        
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBackground(new Color(38, 76, 52));
        userPanel.add(buttonPanel, BorderLayout.EAST);
        userPanel.add(balanceLabel, BorderLayout.CENTER);

        add(trackPanel, BorderLayout.CENTER);
        add(finishPanel, BorderLayout.EAST);
        add(userPanel, BorderLayout.SOUTH);
    }
}
