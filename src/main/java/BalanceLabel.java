
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class BalanceLabel {

    public static JLabel create() {
        JLabel balanceLabel = new JLabel("Available currency: €" + PlayerData.euros);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        balanceLabel.setForeground(Color.ORANGE);
        return balanceLabel;
    }
    
    public static void updateMoney(JLabel balanceLabel) {
    balanceLabel.setText("Available currency: €" + PlayerData.euros);
}
    
}
