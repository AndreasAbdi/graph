package inputProcessing;

import java.io.Reader;
import java.util.Optional;

/**
 * Created by AndreasAbdi on 2016-02-15.
 */
public class UserInputProcessor {
    public Actions processInput(Reader input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }
}
