
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RacePanel extends JPanel {

    JLabel[] horseLabels;
    private Map<Horse, JLabel> horseLabelMap = new HashMap<>();
    private Timer raceTimer;
    private JLabel balanceLabel;

    public RacePanel(Screen screen) {
        setLayout(new BorderLayout());

        JPanel trackPanel = new JPanel(new GridLayout(4, 1, 0, 15));

        String[] lanes = {"/assets/WhiteHorseLowRes.png", "/assets/BlackHorseLowRes.png",
            "/assets/GoldenHorseLowRes.png", "/assets/DiamondHorseLowRes.png"};

        horseLabels = new JLabel[4];

        for (int i = 0; i < 4; i++) {

            JPanel horseLane = new JPanel(null);
            horseLane.setBackground(new Color(196, 122, 58));

            ImageIcon horseIcon = new ImageIcon(
                    getClass().getResource(lanes[i])
            );

            JLabel horseLabel = new JLabel(horseIcon);
            horseLabel.setSize(horseLabel.getPreferredSize());

            horseLabels[i] = horseLabel;
            horseLabel.setLocation(0, 0);

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
                getClass().getResource("/assets/CarrotLowRes.png")
        );
        final ImageIcon waterHose = new ImageIcon(
                getClass().getResource("/assets/WaterHoseLowRes.png")
        );

        JButton carrotButton = new JButton(carrot);
        carrotButton.setHorizontalAlignment(JButton.CENTER);
        carrotButton.setText("€75");
        carrotButton.setHorizontalTextPosition(JButton.CENTER);
        carrotButton.setVerticalTextPosition(JButton.BOTTOM);
        carrotButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton waterHoseButton = new JButton(waterHose);
        waterHoseButton.setHorizontalAlignment(JButton.CENTER);
        waterHoseButton.setText("€125");
        waterHoseButton.setHorizontalTextPosition(JButton.CENTER);
        waterHoseButton.setVerticalTextPosition(JButton.BOTTOM);
        waterHoseButton.setFont(new Font("Arial", Font.BOLD, 18));

        carrotButton.addActionListener(e -> {
            if (PlayerData.race != null) {
                PlayerData.race.purchaseCarrot();
                BalanceLabel.updateMoney(balanceLabel);
            }
        });

        waterHoseButton.addActionListener(e -> {
            if (PlayerData.race != null) {
                PlayerData.race.purchaseWaterHose();
                BalanceLabel.updateMoney(balanceLabel);
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(carrotButton, BorderLayout.EAST);
        buttonPanel.add(waterHoseButton, BorderLayout.WEST);

        balanceLabel = BalanceLabel.create();

        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBackground(new Color(38, 76, 52));
        userPanel.add(buttonPanel, BorderLayout.EAST);
        userPanel.add(balanceLabel, BorderLayout.CENTER);

        add(trackPanel, BorderLayout.CENTER);
        add(finishPanel, BorderLayout.EAST);
        add(userPanel, BorderLayout.SOUTH);
    }

    public void mapHorsesToLabels() {
        horseLabelMap.clear();

        Horse[] horses = PlayerData.race.horses;

        for (int i = 0; i < horses.length; i++) {
            horseLabelMap.put(horses[i], horseLabels[i]);
        }
    }

    public void resetHorsePositions() {
        for (JLabel horseLabel : horseLabels) {
            horseLabel.setLocation(0, horseLabel.getY());
            horseLabel.setVisible(true);
        }
    }

    public void updateHorsePositions() {
        for (Horse horse : horseLabelMap.keySet()) {
            JLabel horseLabel = horseLabelMap.get(horse);

            if (horse.slidOut) {
                horseLabel.setVisible(false);
                continue;
            }

            int laneWidth = horseLabel.getParent().getWidth();
            int farthestPosition = laneWidth - horseLabel.getWidth();

            double position = Math.max(0, Math.min(100, horse.position));
            int x = (int) (position / 100.0 * farthestPosition);

            horseLabel.setLocation(x, horseLabel.getY());
        }
    }

    public void startMovement() {
        if (raceTimer != null) {
            raceTimer.stop();
        }

        BalanceLabel.updateMoney(balanceLabel);

        raceTimer = new Timer(10, e -> {
            updateHorsePositions();
            BalanceLabel.updateMoney(balanceLabel);
        });

        raceTimer.start();
    }

}
