
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartPanel extends JPanel {

    public StartPanel(Screen screen) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Horse betting", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setBorder(new EmptyBorder(100, 0, 0, 0));

        ImageIcon horseIcon = new ImageIcon(
                getClass().getResource("/assets/WhiteHorseLowRes.png")
        );

        JLabel horseLabel = new JLabel(horseIcon);
        horseLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton startButton = new JButton("Start game");
        startButton.setBorder(new EmptyBorder(15, 40, 15, 40));
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 100, 0));

        add(titleLabel, BorderLayout.NORTH);
        add(horseLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        startButton.addActionListener(e -> screen.showSetup());
    }

}
