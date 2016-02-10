package graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Graph {
    public static void main (String[] args) {
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        //Build the graph connection
        GraphDatabaseService graphDb = new GraphDatabaseFactory()
                .newEmbeddedDatabase()
    }
}
