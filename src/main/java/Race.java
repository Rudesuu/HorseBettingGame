
public class Race {
    static final int UPDATES_PER_SECOND = 100;
    private static final double UPDATE_TIME = 1_000_000_000.0 / UPDATES_PER_SECOND;
    
    Horse[] horses;
    long startTime = -1;
    long finishingTime = -1;
    boolean wet = false;
    
    Race() {
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
            Iteration(currentTime, deltaTime);
            
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
    
    void Iteration(long currentTime, double deltaTime) {
        for (Horse horse : horses) {
            horse.updatePosition(deltaTime);
            horse.updateFinished(currentTime);
        }
        
        long finishingTime = checkAllHorsesFinished();
        if (finishingTime != -1) {
            this.finishingTime = finishingTime;
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
}
