package inputProcessing;

import gameData.impl.ChampionDataImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
public class Actions {

    private ChampionDataImpl championDataImpl;
    private List<String> championsToLoadCounters;
    private boolean shouldResetDatabase;
    private boolean shouldDownloadDatabase;
    private boolean shouldLoadCounters;

    public Actions() {
        this(new ChampionDataImpl());
    }

    public Actions(ChampionDataImpl championDataImpl) {
        this.championDataImpl = championDataImpl;
        this.championsToLoadCounters = new ArrayList<String>();
        this.shouldDownloadDatabase = false;
        this.shouldResetDatabase = false;
        this.shouldLoadCounters = false;
    }

    public void loadCounters(List<String> championList) {
        if (championList == null) {
            throw new IllegalArgumentException("No champions given");
        }
        for (String champion: championList) {
            setLoadCountersState(champion);
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

    private void setLoadCountersState (String champion) {
        if (championDataImpl.isChampion(champion)) {
            championsToLoadCounters.add(champion);
            shouldLoadCounters = true;
        }
    }

    public List<String> getChampionsToLoadCounters() {
        return championsToLoadCounters;
    }
}
