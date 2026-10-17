
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RaceSetupPanel extends JPanel {

    private JLabel balanceLabel;

    public RaceSetupPanel(Screen screen) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Prepare for the race", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));

        final ImageIcon whiteHorse = new ImageIcon(
                getClass().getResource("/assets/WhiteHorseLowRes.png")
        );
        final ImageIcon blackHorse = new ImageIcon(
                getClass().getResource("/assets/BlackHorseLowRes.png")
        );
        final ImageIcon goldenHorse = new ImageIcon(
                getClass().getResource("/assets/GoldenHorseLowRes.png")
        );
        final ImageIcon diamondHorse = new ImageIcon(
                getClass().getResource("/assets/DiamondHorseLowRes.png")
        );

        JButton whiteHorseButton = createHorseButton(whiteHorse, "White horse", 100);
        JButton blackHorseButton = createHorseButton(blackHorse, "Black horse", 150);
        JButton goldenHorseButton = createHorseButton(goldenHorse, "Golden horse", 175);
        JButton diamondHorseButton = createHorseButton(diamondHorse, "Diamond horse", 200);

        JPanel horsesPanel = new JPanel(new GridLayout(2, 2, 0, 0));

        horsesPanel.add(whiteHorseButton);
        horsesPanel.add(blackHorseButton);
        horsesPanel.add(goldenHorseButton);
        horsesPanel.add(diamondHorseButton);

        JButton startRaceButton = new JButton("Start race");
        startRaceButton.setBorder(new EmptyBorder(15, 100, 15, 100));
        startRaceButton.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.add(startRaceButton);
        startButtonPanel.setBorder(new EmptyBorder(20, 0, 0, 65));

        JPanel selectedHorsePanel = new JPanel();
        selectedHorsePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel selectedTitle = new JLabel("Your selected horse:");
        selectedTitle.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel selectedHorseName = new JLabel("Diamond horse");
        selectedHorseName.setFont(new Font("Arial", Font.PLAIN, 18));
        PlayerData.chosenHorse = "Diamond Horse";

        whiteHorseButton.addActionListener(e -> {
            selectedHorseName.setText("White horse");
            PlayerData.chosenHorse = "White Horse";
        });

        blackHorseButton.addActionListener(e -> {
            selectedHorseName.setText("Black horse");
            PlayerData.chosenHorse = "Black Horse";
        });

        goldenHorseButton.addActionListener(e -> {
            selectedHorseName.setText("Golden horse");
            PlayerData.chosenHorse = "Golden Horse";
        });

        diamondHorseButton.addActionListener(e -> {
            selectedHorseName.setText("Diamond horse");
            PlayerData.chosenHorse = "Diamond Horse";
        });

        selectedHorsePanel.add(selectedTitle);
        selectedHorsePanel.add(selectedHorseName);

        JPanel bettingPanel = new JPanel();
        bettingPanel.setLayout(new GridLayout(3, 1, 5, 5));

        balanceLabel = BalanceLabel.create();

        JLabel betLabel = new JLabel("Bet amount (€):");
        betLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField betField = new JTextField("50");
        betField.setFont(new Font("Arial", Font.PLAIN, 16));

        bettingPanel.add(balanceLabel);
        bettingPanel.add(betLabel);
        bettingPanel.add(betField);
        bettingPanel.setBorder(new EmptyBorder(20, 0, 0, 60));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(selectedHorsePanel, BorderLayout.WEST);
        bottomPanel.add(startButtonPanel, BorderLayout.CENTER);
        bottomPanel.add(bettingPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
        add(titleLabel, BorderLayout.NORTH);
        add(horsesPanel, BorderLayout.CENTER);

        startRaceButton.addActionListener(e -> {
            int bet;

            try {
                bet = Integer.parseInt(betField.getText().trim());
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(
                        this, "Please enter a valid whole number."
                );
                return;
            }

            if (bet <= 0) {
                javax.swing.JOptionPane.showMessageDialog(
                        this, "Your bet must be greater than zero."
                );
                return;
            }

            if (bet > PlayerData.euros) {
                javax.swing.JOptionPane.showMessageDialog(
                        this, "You don't have enough money for that bet."
                );
                return;
            }

            PlayerData.race = new Race(bet);
            PlayerData.euros -= bet;
            BalanceLabel.updateMoney(balanceLabel);

            screen.prepareRace();

            Thread raceThread = new Thread(() -> {
                PlayerData.race.startRace();
            });

            raceThread.start();
        });
    }

    private JButton createHorseButton(ImageIcon icon, String name, int speed) {
        JButton button = new JButton(icon);
        button.setText("<html>" + name + "<br>Speed: " + speed + "</html>");
        button.setHorizontalAlignment(JButton.CENTER);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    public void refreshBalance() {
        BalanceLabel.updateMoney(balanceLabel);
    }

}
