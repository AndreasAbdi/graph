package inputProcessing;

import gameData.ChampionData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
//TODO:
public class ActionsTest {
    Actions testObject;
    List<String> championList;

    @Before
    public void setup() {
        ChampionData championData = new ChampionData();
        championData.addChampion("=erk=");
        championData.addChampion("giraffe");
        testObject = new Actions(championData);
        championList = new ArrayList<>();
    }

    @Test
    public void shouldNotRequireAnythingWhenUsingNullConstructor() {
        testObject = new Actions();
        assertFalse(testObject.isShouldDownloadDatabase());
        assertFalse(testObject.isShouldLoadCounters());
        assertFalse(testObject.isShouldResetDatabase());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNullInputToLoadCounters() {
        championList = null;
        testObject.loadCounters(championList);
    }

    @Test
    public void shouldReturnDoNotLoadCountersWhenGivenEmptyListOfString() {
        testObject.loadCounters(championList);
        assertFalse(testObject.isShouldLoadCounters());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNonValidListOfString() {
        championList.add("asdfa");
        championList.add("asds");
        testObject.loadCounters(championList);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNonValidListOfEmptyString() {
        championList.add("");
        testObject.loadCounters(championList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNonValidListOfEmptyStrings() {
        championList.add("");
        championList.add("");
        testObject.loadCounters(championList);
    }

    @Test
    public void shouldReturnQueryRequiredWhenSameInputGivenInListOfString() {
        championList.add("fizz");
        championList.add("fizz");
        testObject.loadCounters(championList);
        assertTrue(testObject.isShouldLoadCounters());
    }

    @Test
    public void shouldReturnQueryRequiredWhenGivenValidListOfString() {
        championList.add("zed");
        championList.add("AKALI");
        testObject.loadCounters(championList);
        assertTrue(testObject.isShouldLoadCounters());
    }


}
