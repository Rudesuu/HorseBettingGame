
import java.awt.Label;


public class Horse {
    String name;
    float payout;
    double position = 0; // in percentage of screen's width
    float speed; // in percentage of screen's width per second
    int chanceToSlideOut;
    boolean slidOut = false;
    long finishingTime;
    Label label;
    
    Horse(String name, float payout, float speed, int chanceToSlideOut, String imagePath) {
        this.name = name;
        this.payout = payout;
        this.speed = speed;
        this.chanceToSlideOut = chanceToSlideOut;
        label = createLabel(imagePath);
    }
    
    void updatePosition(double deltaTime) {
        position += (double) speed * deltaTime;
    }
    
    static Horse[] createRaceHorses() {
        Horse whiteHorse = new Horse("White Horse", 10f, 1f, 10, "/assets/WhiteHorseLowRes.png");
        Horse blackHorse = new Horse("Black Horse", 4.1f, 1.5f, 15, "" /* TODO */);
        Horse goldenHorse = new Horse("Golden Horse", 1.9f, 1.75f, 25, "" /* TODO */);
        Horse diamondHorse = new Horse("Diamond Horse", 1.1f, 2f, 40, "" /* TODO */);
        
        Horse[] horses = new Horse[4];
        horses[0] = whiteHorse;
        horses[1] = blackHorse;
        horses[2] = goldenHorse;
        horses[3] = diamondHorse;
        
        return horses;
    }
    
    static Label createLabel(String imagePath) {
        // TODO
        return null;
    }
}
