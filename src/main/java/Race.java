
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
            Horse[] sortedHorses = getSortedHorsesOnFinishingTime();

            String[][] results = new String[4][5];

            for (int i = 0; i < sortedHorses.length; i++) {
                String position = Integer.toString(i);

                if (i % 100 >= 11 && i % 100 <= 13) {
                    position += "th";
                } else {
                    switch (i % 10) {
                        case 1:
                            position += "st";
                            break;
                        case 2:
                            position += "nd";
                            break;
                        case 3:
                            position += "rd";
                            break;
                        default:
                            position += "th";
                    }
                }

                results[i][0] = position;

                Horse horse = sortedHorses[i];

                results[i][1] = horse.name;

                if (horse.finishingTime == -1) {
                    results[i][2] = "DNF";
                    continue;
                }

                long delta = horse.finishingTime - startTime;

                long totalSeconds = delta / 1_000_000_000L;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;

                String convertedDelta = "%02d:%02d".formatted(minutes, seconds);
                results[i][2] = convertedDelta;

                // We know that the horse is finished because otherwise we would not have calculated the delta.
                PlayerData.HorseStats stats = PlayerData.statsPerHorse.get(horse.name);

                if (i == 0) {
                    stats.wins++;
                }

                if (stats.fastestTime == -1 || delta < stats.fastestTime) {
                    stats.fastestTime = delta;
                    stats.convertedFastestTime = convertedDelta;
                }

                results[i][3] = stats.convertedFastestTime;
                results[i][4] = Integer.toString(stats.wins);
            }
            
            GameFrame.screen.showLeaderboard(results, (int) givePayout(sortedHorses[0]));
        }
    }

    void applyWaterHose() {
        for (Horse horse : horses) {
            horse.applyWaterHose();
        }
    }

    long checkAllHorsesFinished() {
        long slowestTime = -1;
        boolean allSlidOut = true;
        for (Horse horse : horses) {
            if (horse.slidOut) {
                continue;
            } else if (horse.finishingTime == -1) {
                return -1;
            }

            allSlidOut = false;

            slowestTime = Math.max(slowestTime, horse.finishingTime);
        }

        if (allSlidOut) {
            return -2;
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

    float givePayout(Horse firstHorse) {
        float earnings = 0;
        if (firstHorse == PlayerData.getHorseObject() && finishingTime != -2) {
            earnings = bettedEuros * PlayerData.getHorseObject().payout;
            PlayerData.euros += earnings;
        }
        return earnings - bettedEuros;
    }
}
