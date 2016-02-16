package gameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
//TODO: Maybe put a provider class in front of this?
//TODO: update this class to take champion data automatically.
public class ChampionData {
    private List<String> champions;

    public ChampionData() {
        champions = new ArrayList<String>();
    }

    public void addChampion(String champion) {
       this.champions.add(champion);
    }

    public void addChampions(List<String> champions) {

    }

    public List<String> getChampions() {
        return champions;
    }

    public void setChampions(List<String> champions) {
        this.champions = champions;
    }
}
