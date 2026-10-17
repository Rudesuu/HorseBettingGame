
import java.util.Arrays;

public class Race {

    static final int UPDATES_PER_SECOND = 100;
    static final double UPDATE_TIME = 1_000_000_000.0 / UPDATES_PER_SECOND;

    static final int CARROT_COST = 75;
    static final int WATERHOSE_COST = 125;

    int bettedEuros;

    Horse[] horses;
    long startTime = -1;
    long finishingTime = -1;
    boolean wet = false;

    Race(int bettedEuros) {
        this.bettedEuros = bettedEuros;
        this.horses = Horse.createRaceHorses();
    }

    void startRace() {
        startTime = System.nanoTime();

        long lastTime = startTime;
        double deltaTime = 0;

        while (finishingTime == -1) {
            long currentTime = System.nanoTime();

            deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            // ITERATION
            iteration(currentTime, deltaTime);

            long elapsed = System.nanoTime() - currentTime;
            long sleepTime = (long) UPDATE_TIME - elapsed;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(
                            sleepTime / 1_000_000,
                            (int) (sleepTime % 1_000_000)
                    );
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    void iteration(long currentTime, double deltaTime) {
        for (Horse horse : horses) {
            horse.updatePosition(deltaTime);
            horse.updateFinished(currentTime);
        }

        long finishingTime = checkAllHorsesFinished();
        if (finishingTime != -1) {
            this.finishingTime = finishingTime;

            //GameFrame.screen.showLeaderboard(new String[][]{
            //{"1st", "Golden horse", "47.07s"},
            //{"2nd", "Black horse", "53.87s"},
            //{"3rd", "White horse", "62.34s"},
            //{"4th", "Diamond horse", "DNF"}
            //});
            Horse[] sortedHorses = getSortedHorsesOnFinishingTime();
            if (sortedHorses[0] == PlayerData.getHorseObject()) {
                givePayout();
            }
        }
    }

    void applyWaterHose() {
        for (Horse horse : horses) {
            horse.applyWaterHose();
        }
    }

    long checkAllHorsesFinished() {
        long slowestTime = -1;
        for (Horse horse : horses) {
            if (horse.slidOut) {
                continue;
            } else if (horse.finishingTime == -1) {
                return -1;
            }

            slowestTime = Math.max(slowestTime, horse.finishingTime);
        }

        return slowestTime;
    }

    void purchaseCarrot() {
        if (PlayerData.euros < CARROT_COST) {
            return;
        }
        Horse horse = PlayerData.getHorseObject();
        PlayerData.euros -= CARROT_COST;
        horse.applyCarrotSpeedBoost();
    }

    void purchaseWaterHose() {
        if (PlayerData.euros < WATERHOSE_COST) {
            return;
        }
        PlayerData.euros -= WATERHOSE_COST;
        applyWaterHose();
    }

    Horse[] getSortedHorsesOnFinishingTime() {
        Horse[] sortedHorses = horses.clone();

        Arrays.sort(sortedHorses, (h1, h2) -> {
            if (h1.finishingTime == -1 && h2.finishingTime == -1) {
                return 0;
            }
            if (h1.finishingTime == -1) {
                return 1;
            }
            if (h2.finishingTime == -1) {
                return -1;
            }

            return Long.compare(h1.finishingTime, h2.finishingTime);
        });

        return sortedHorses;
    }

    void givePayout() {
        PlayerData.euros += bettedEuros * PlayerData.getHorseObject().payout;
    }
}
