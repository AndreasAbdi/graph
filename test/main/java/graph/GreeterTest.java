package graph;

import org.junit.Test;

import static org.junit.Assert.*;


public class GreeterTest {

    @Test
    public void testSayHello() throws Exception {
        Greeter greeter = new Greeter();
        assertNotNull(greeter.sayHello());
    }
}