package inputProcessing;

import gameData.impl.ChampionDataImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
//TODO: mock the championData object.
public class ActionsTest {
    private Actions testObject;
    private List<String> championList;

    @Before
    public void setup() {
        givenTheTestObjectHasPreexistingChampionData();
        championList = new ArrayList<>();
    }

    @Test
    public void shouldNotRequireAnythingWhenUsingNullConstructor() {
        givenTheTestHasNoPreexistingChampionData();
        shouldNotHaveActionFlagsSet();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNullInputToLoadCounters() {
        championList = null;
        testObject.loadCounters(championList);
    }

    @Test
    public void shouldNotHaveLoadCountersFlagSetGivenEmptyStringList() {
        testObject.loadCounters(championList);
        assertFalse(testObject.isShouldLoadCounters());
    }

    @Test
    public void shouldNotHaveLoadCountersFlagSetGivenInvalidStringList() {
        assertFalse(testObject.isShouldLoadCounters());
    }


    @Test
    public void shouldNotHaveLoadCountersFlagSetGivenInvalidEmptyStringList() {
        championList.add("");
        testObject.loadCounters(championList);
        assertFalse(testObject.isShouldLoadCounters());

    }

    @Test
    public void shouldHaveLoadCounterFlagGivenValidRepeatedStringList() {
        givenValidRepeatedStringList();
        assertTrue(testObject.isShouldLoadCounters());
    }


    @Test
    public void shouldHaveLoadCounterFlagSetGivenValidStringList() {
        givenValidStringList();
        testObject.loadCounters(championList);
        assertTrue(testObject.isShouldLoadCounters());
    }

    @Test
    public void shouldHaveLoadCounterFlagSetGivenValidMultipleStringList() {
        championList = generateChampionList();
        testObject.loadCounters(championList);
        assertTrue(testObject.isShouldLoadCounters());
    }

    @Test
    public void shouldHaveSameCountersSetAsGivenValidCounters() {
        championList = generateChampionList();
        testObject.loadCounters(championList);
        assertTrue(testObject.isShouldLoadCounters());
        assertEquals(championList, testObject.getChampionsToLoadCounters());
        assertFalse(testObject.getChampionsToLoadCounters().contains("sof 23"));
    }

    private void givenTheTestObjectHasPreexistingChampionData() {
        testObject = new Actions(generateChampionData());
    }

    private void givenTheTestHasNoPreexistingChampionData() {
        testObject = new Actions();
    }

    private void givenValidStringList() {
        String champion = retrieveValidChampion();
        List<String> championList = new ArrayList<>();
        championList.add(champion);
        testObject.loadCounters(championList);
    }

    private void givenValidRepeatedStringList() {
        String champion = retrieveValidChampion();
        List<String> repeatedChampionList = new ArrayList<>();
        repeatedChampionList.add(champion);
        repeatedChampionList.add(champion);
        testObject.loadCounters(repeatedChampionList);
    }

    private void givenInvalidStringList() {
        givenInvalidStringList();
        championList.add("asdfa");
        championList.add("asds");
        testObject.loadCounters(championList);
    }

    private void shouldNotHaveActionFlagsSet() {
        assertFalse(testObject.isShouldDownloadDatabase());
        assertFalse(testObject.isShouldLoadCounters());
        assertFalse(testObject.isShouldResetDatabase());
    }

    private ChampionDataImpl generateChampionData() {
        ChampionDataImpl championDataImpl = new ChampionDataImpl();
        championDataImpl.setChampions(generateChampionList());
        return championDataImpl;
    }

    private List<String> generateChampionList() {
        List<String> championList = new ArrayList<String>();
        championList.add("=erk");
        championList.add("giraffe");
        return championList;
    }

    private String retrieveValidChampion() {
        List<String> pregeneratedChampionList = generateChampionList();
        List<String> repeatedChampionList = new ArrayList<>();
        return pregeneratedChampionList.isEmpty() ? null : pregeneratedChampionList.get(0) ;
    }
}
