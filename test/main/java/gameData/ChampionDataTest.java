package gameData;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGIvenNUllStringToChampionList() {
        testObject.addChampion("safsdf");
        testObject.addChampion(null);
    }

    @Test
    public void shouldContainSameChampionAsChampionPassed() {
        String champion = "sdfsd";
        testObject.addChampion(champion);
        assertTrue(testObject.isChampion(champion));
    }

    @Test
    public void shouldContainAllChampionsPassedAsArguments() {
        List<String> championList = new ArrayList<>(3);
        championList.add("");
        championList.add("asdfasdf");
        championList.add("savojn  vwiuohw ovw ");
        championList.add("(null)");
        championList.add("Ṱ̺̺̕o͞ ̷i̲̬͇̪͙n̝̗͕v̟̜̘̦͟o̶̙̰̠kè͚̮̺̪̹̱̤ ̖t̝͕̳̣̻̪͞h̼͓̲̦̳̘̲e͇̣̰̦̬͎ ̢̼̻̱̘h͚͎͙̜̣̲ͅi̦̲̣̰̤v̻͍e̺̭̳̪̰-m̢iͅn̖̺̞̲̯̰d̵̼̟͙̩̼̘̳ ̞̥̱̳̭r̛̗̘e͙p͠r̼̞̻̭̗e̺̠̣͟s̘͇̳͍̝͉e͉̥̯̞̲͚̬͜ǹ̬͎͎̟̖͇̤t͍̬̤͓̼̭͘ͅi̪̱n͠g̴͉ ͏͉ͅc̬̟h͡a̫̻̯͘o̫̟̖͍̙̝͉s̗̦̲.̨̹͈̣");
        championList.add("  \"The quic\\b\\b\\b\\b\\b\\bk brown fo\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007\\u0007x... [Beeeep]\", \n");
        for (String champion : championList) {
            testObject.addChampion(champion);
        }

        for (String champion : championList) {
           assertTrue(testObject.isChampion(champion));
        }
    }

    @Test
    public void shouldNotContainChampionsNotAdded() {
        List<String> championList = new ArrayList<>(4);
        championList.add("");
        championList.add("RomansInSussex.co.uk");
        championList.add("<IMG SRC=\\\"jav&#x09;ascript:alert('XSS');\\\"> ");
        championList.add(null);
        for (String champion : championList) {
            assertFalse(testObject.isChampion(champion));
        }
    }


    @Test
    public void shouldRecognizeProperlyChampionsAdded() {
        int numChampions = 5;
        List<String> championList = new ArrayList<>(numChampions);
        championList.add("Ｔｈｅ ｑｕｉｃｋ ｂｒｏｗｎ ｆｏｘ ｊｕｍｐｓ ｏｖｅｒ ｔｈｅ ｌａｚｙ ｄｏｇ");
        championList.add("\\\"");
        championList.add("₀₁₂");
        championList.add(" \"1.0/0.0\"");
        championList.add("NaN");
        for (int i = 0 ; i < numChampions; i+=2) {
            testObject.addChampion(championList.get(i));
        }
        for (int i = 1; i < numChampions; i+=2) {
            assertFalse(testObject.isChampion(championList.get(i)));
        }
        for (int i = 0; i < numChampions; i+=2) {
            assertTrue(testObject.isChampion(championList.get(i)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNullListToChampionsList() {
        testObject.addChampions(null);
    }

    //TODO
    @Test
    public void shouldContainAllChampionListWhenBothMethodsAreUsed() {
        List<String> championList = new ArrayList<>();
        championList.add("sfdsf");
        championList.add(" afiwoje 2 3o 39 ");
        championList.add("234*//");
        testObject.addChampions(championList);
        testObject.addChampion("xabr12ng02z.");

        assertTrue(testObject.isChampion("xabr12ng02z."));
        for (String champion : championList) {
            assertTrue(testObject.isChampion(champion));
        }
    }
}
