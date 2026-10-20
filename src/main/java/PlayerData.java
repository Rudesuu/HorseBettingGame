
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class PlayerData {

    static int euros = 500;
    static String chosenHorse;
    
    static Map<String, HorseStats> statsPerHorse = new HashMap();

    static Race race;

    private PlayerData() {
    }

    void startNewRace(int bettedEuros) {
        if (race != null && race.finishingTime == -1) {
            return;
        }
        if (bettedEuros > euros) {
            return;
        }
        PlayerData.euros -= bettedEuros;

        race = new Race(bettedEuros);

        race.startRace();
    }

    static Horse getHorseObject() {
        if (race == null) {
            return null;
        }

        for (Horse horse : race.horses) {
            if (!horse.name.equals(chosenHorse)) {
                continue;
            }
            return horse;
        }

        return null;
    }

    static class HorseStats {

        int wins = 0;
        long fastestTime = -1;
        String convertedFastestTime = "DNF";
    }

    private static final Path SAVE_FILE = Path.of("saveData.json");
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void save() throws IOException {
        SaveData data = new SaveData();
        data.euros = euros;
        data.statsPerHorse = statsPerHorse;

        Files.writeString(SAVE_FILE, GSON.toJson(data));
    }

    public static void load() throws IOException {
        if (!Files.exists(SAVE_FILE)) {
            statsPerHorse.put("White Horse", new HorseStats());
            statsPerHorse.put("Black Horse", new HorseStats());
            statsPerHorse.put("Golden Horse", new HorseStats());
            statsPerHorse.put("Diamond Horse", new HorseStats());
            return;
        }

        SaveData data = GSON.fromJson(
                Files.readString(SAVE_FILE),
                SaveData.class
        );

        if (data == null) {
            return;
        }

        euros = data.euros;
        if (data.statsPerHorse != null) {
            statsPerHorse = data.statsPerHorse;
        } else {
            statsPerHorse.put("White Horse", new HorseStats());
            statsPerHorse.put("Black Horse", new HorseStats());
            statsPerHorse.put("Golden Horse", new HorseStats());
            statsPerHorse.put("Diamond Horse", new HorseStats());
        }
    }

    private static class SaveData {

        int euros;
        Map<String, HorseStats> statsPerHorse;
    }
}
