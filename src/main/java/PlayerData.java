
public final class PlayerData {
    static int euros;
    static String chosenHorse;
    
    static Race race;
    
    private PlayerData() {};
    
    void startNewRace(int bettedEuros) {
        if (race != null && race.finishingTime == -1) return;
        if (bettedEuros > euros) return;
        PlayerData.euros -= bettedEuros;
        
        race = new Race(bettedEuros);
        
        race.startRace();
    }
    
    static Horse getHorseObject() {
        if (race == null) return null;
        
        for (Horse horse : race.horses) {
            if (!horse.name.equals(chosenHorse)) continue;
            return horse;
        }
        
        return null;
    }
}
