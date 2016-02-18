package inputProcessing;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
public class UserInputProcessorTest {
/*
List of tests.
1. TODO:Should be able to take the user input of reset database.
2. TODO:Should be able to take an input to do specific processing
3. Should be able to take a null and not terminate.
4. TODO:Should be able to take weird utf-8 characters.
5. TODO:Should be able to take inputs from weird data.
 */
    private UserInputProcessor testObject;
    private Reader input;

    @Before
    public void setup() {
        testObject = new UserInputProcessor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenNullInput() throws Exception {
        testObject.processInput(null);
    }

    @Test
    public void shouldReturnUnsetActionsWhenGivenEmptyInput() {
        input = new StringReader("");
        Actions actions = testObject.processInput(input);
        allActionStatusesAreInvalid(actions);
    }

    @Test
    public void shouldReturUnsetActionsWhenGivenInvalidInput() {
        input = new StringReader("hahahahahosdfsodfisodf");
        Actions actions = testObject.processInput(input);
        allActionStatusesAreInvalid(actions);
    }

    public void allActionStatusesAreInvalid(Actions actions) {
        assertFalse(actions.isShouldDownloadDatabase());
        assertFalse(actions.isShouldResetDatabase());
        assertFalse(actions.isShouldLoadCounters());
    }

}
