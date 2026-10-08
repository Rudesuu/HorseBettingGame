
public class Race {
    Horse[] horses;
    long startTime = -1;
    long finishingTime = -1;
    boolean wet = false;
    
    Race() {
        this.horses = Horse.createRaceHorses();
    }
    
    void startRace() {
        startTime = System.currentTimeMillis();
        
        while (finishingTime == -1) {
            Iteration();
        }
    }
    
    void Iteration() {
        
        
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
