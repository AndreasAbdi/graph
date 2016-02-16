package gameData;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
public class ChampionDataTest {
    ChampionData testObject;

    @Before
    public void setup() {
        testObject = new ChampionData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNullStringToChampionsList() {
        testObject.addChampion(null);
    }

    @Test
    public void shouldPassSameChampionWhenChampionIsPassed() {

    }

    @Test
    public void shouldPassAllChampionsWhenMultipleChampionsArePassed() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNullListToChampionsList() {
        testObject.addChampions(null);
    }

    //TODO
    @Test
    public void shouldReturnSameChampionListWhenBothMethodsAreUsed() {

    }
}
