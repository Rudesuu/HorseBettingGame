
import java.awt.Font;
import javax.swing.JLabel;

public class BalanceLabel {

    public static JLabel create() {
        JLabel balanceLabel = new JLabel("Available currency: €500");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        return balanceLabel;
    }
}
