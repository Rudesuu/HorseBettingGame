
import java.util.Random;

public class Horse {

    String name;
    float payout;
    double position = 0; // in percentage of screen's width
    float speed; // in percentage of screen's width per second
    int chanceToSlideOut;
    boolean slidOut = false;
    long finishingTime = -1;

    Horse(String name, float payout, float speed, int chanceToSlideOut) {
        this.name = name;
        this.payout = payout;
        this.speed = speed;
        this.chanceToSlideOut = chanceToSlideOut;
    }

    void updatePosition(double deltaTime) {
        position += Math.min((double) speed * deltaTime, 100);
    }

    void updateFinished(long currentTime) {
        if (finishingTime != -1) {
            return;
        }
        if (position < 100) {
            return;
        }

        finishingTime = currentTime;
    }

    void applyCarrotSpeedBoost() {
        speed *= 1.1;
    }

    void applyWaterHose() {
        if (slidOut || finishingTime != -1) {
            return;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        if (randomNumber >= chanceToSlideOut) {
            return;
        }
        slidOut = true;
    }

    static Horse[] createRaceHorses() {
        Horse whiteHorse = new Horse("White Horse", 10f, 2f, 10);
        Horse blackHorse = new Horse("Black Horse", 4.1f, 3f, 15);
        Horse goldenHorse = new Horse("Golden Horse", 1.9f, 3.5f, 25);
        Horse diamondHorse = new Horse("Diamond Horse", 1.1f, 4f, 40);

        Horse[] horses = new Horse[4];
        horses[0] = whiteHorse;
        horses[1] = blackHorse;
        horses[2] = goldenHorse;
        horses[3] = diamondHorse;

        return horses;
    }
}
