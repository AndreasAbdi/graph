package gameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Abdi on 2016-02-15.
 */
//TODO: Maybe put a provider class in front of this?
//TODO: update this class to take champion data automatically.
public class ChampionData {
    private List<String> champions;

    public ChampionData() {

        champions = new ArrayList<String>();
    }

    public void addChampion(String champion) {
        if (champion == null) {
            throw new IllegalArgumentException("invalid champion string input. ");
        }
        this.champions.add(champion);
    }

    public void addChampions(List<String> championList) {
        if (championList == null) {
            throw new IllegalArgumentException("invalid champion string input. ");
        }
        for (String champion: championList) {
            this.champions.add(champion);
        }
    }

    public void setChampions(List<String> champions) {
        this.champions = champions;
    }

    public boolean isChampion(String champion) {
        return this.champions.contains(champion);
    }
}
