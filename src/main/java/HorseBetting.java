
public class HorseBetting {

    public static void main(String[] args) {
        try {
            PlayerData.load();
        } catch (Exception e) {
            // Intentionally ignored
        }

        GameFrame frame = new GameFrame();
        frame.setVisible(true);
    }
}
