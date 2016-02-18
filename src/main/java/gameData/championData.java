package gameData;

import java.util.List;

/**
 * Created by AndreasAbdi on 2016-02-17.
 */
public interface ChampionData {
    public void addChampion(String champion);
    public void addChampions(List<String> championList);
    public void setChampions(List<String> championList);
    public boolean isChampion(String champion);
}
