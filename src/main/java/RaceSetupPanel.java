
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

    public RaceSetupPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Prepare for the race", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

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

        JButton whiteHorseButton = new JButton(whiteHorse);
        whiteHorseButton.setHorizontalAlignment(JButton.CENTER);
        whiteHorseButton.setText("<html>White horse<br>&nbsp;Speed: 100</html>");
        whiteHorseButton.setHorizontalTextPosition(JButton.CENTER);
        whiteHorseButton.setVerticalTextPosition(JButton.BOTTOM);
        whiteHorseButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton blackHorseButton = new JButton(blackHorse);
        blackHorseButton.setHorizontalAlignment(JButton.CENTER);
        blackHorseButton.setText("<html>Black horse<br>&nbsp;Speed: 150</html>");
        blackHorseButton.setHorizontalTextPosition(JButton.CENTER);
        blackHorseButton.setVerticalTextPosition(JButton.BOTTOM);
        blackHorseButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton goldenHorseButton = new JButton(goldenHorse);
        goldenHorseButton.setHorizontalAlignment(JButton.CENTER);
        goldenHorseButton.setText("<html>Golden horse<br>&nbsp;&nbsp;Speed: 175</html>");
        goldenHorseButton.setHorizontalTextPosition(JButton.CENTER);
        goldenHorseButton.setVerticalTextPosition(JButton.BOTTOM);
        goldenHorseButton.setFont(new Font("Arial", Font.BOLD, 18));

        JButton diamondHorseButton = new JButton(diamondHorse);
        diamondHorseButton.setHorizontalAlignment(JButton.CENTER);
        diamondHorseButton.setText("<html>Diamond horse<br>&nbsp;&nbsp;&nbsp;Speed: 200</html>");
        diamondHorseButton.setHorizontalTextPosition(JButton.CENTER);
        diamondHorseButton.setVerticalTextPosition(JButton.BOTTOM);
        diamondHorseButton.setFont(new Font("Arial", Font.BOLD, 18));

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

        //This should change according to the selected horse
        JLabel selectedHorseName = new JLabel("Diamond horse");
        selectedHorseName.setFont(new Font("Arial", Font.PLAIN, 18));

        selectedHorsePanel.add(selectedTitle);
        selectedHorsePanel.add(selectedHorseName);

        JPanel bettingPanel = new JPanel();
        bettingPanel.setLayout(new GridLayout(3, 1, 5, 5));
        
        JLabel balanceLabel = BalanceLabel.create();

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
    }
}
