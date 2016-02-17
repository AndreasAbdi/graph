package inputProcessing;

import gameData.ChampionData;

import java.util.List;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
public class Actions {

    private ChampionData championData;
    private boolean shouldResetDatabase;
    private boolean shouldDownloadDatabase;
    private boolean shouldLoadCounters;

    public Actions() {
        this(new ChampionData());
    }

    public Actions(ChampionData championData) {
        this.championData = championData;
        shouldDownloadDatabase = false;
        shouldResetDatabase = false;
        shouldLoadCounters = false;
    }

    public void loadCounters(List<String> championList) {
        if (championList == null) {
            throw new IllegalArgumentException("No champions given");
        }
    }

    public boolean isShouldDownloadDatabase() {
        return shouldDownloadDatabase;
    }

    public void setShouldDownloadDatabase(boolean shouldDownloadDatabase) {
        this.shouldDownloadDatabase = shouldDownloadDatabase;
    }

    public boolean isShouldResetDatabase() {
        return shouldResetDatabase;
    }

    public void setShouldResetDatabase(boolean shouldResetDatabase) {
        this.shouldResetDatabase = shouldResetDatabase;
    }

    public boolean isShouldLoadCounters() {
        return shouldLoadCounters;
    }
}
