package graph;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.InputStream;

public class Graph {

    public enum NodeType implements Label {
        person, Course;
    }

    public enum RelationType implements RelationshipType {
        Knows, BelongsTo;
    }


    public static void main (String[] args) {
        Graph graph = new Graph();
        InputStream jsonData= graph.getJsonData();
        graph.parseJsonData(jsonData);
    }

    private void parseJsonData(InputStream jsonData) {
        //TODO
    }

    private InputStream getJsonData() {
        //TODO
        return null;
    }

    private void doGraphStuff() {
        GraphDatabaseFactory graphDatabaseFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDatabaseFactory.newEmbeddedDatabase("/Users/AndreasAbdi/Documents/Neo4j/default.graphdb");

        try(Transaction tx = graphDb.beginTx()) {
            Node bobNode = graphDb.createNode(NodeType.person);
            bobNode.setProperty("pid", 123123);
            bobNode.setProperty("name", "bob");
            bobNode.setProperty("age", 12);

            Node aliceNode = graphDb.createNode(NodeType.person);
            aliceNode.setProperty("pid", 423);
            aliceNode.setProperty("name", "alice");
            aliceNode.setProperty("age", 17);

            Node eveNode = graphDb.createNode(NodeType.person);
            eveNode.setProperty("name", "Eve");

            Node itNode = graphDb.createNode(NodeType.Course);
            itNode.setProperty("id", 1);
            itNode.setProperty("name", "it beginners");
            itNode.setProperty("location", "room 12");

            Node engNode = graphDb.createNode(NodeType.Course);
            engNode.setProperty("id", 2);
            engNode.setProperty("name", "eng beginners");
            engNode.setProperty("location", "room 141");

            bobNode.createRelationshipTo(aliceNode, RelationType.Knows);

            Relationship BobRelIt = bobNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            BobRelIt.setProperty("function", "student");

            Relationship BobRelElEng = bobNode.createRelationshipTo(engNode, RelationType.BelongsTo);
            BobRelElEng.setProperty("function", "supply teacher");

            Relationship aliceRelIt = aliceNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            aliceRelIt.setProperty("function", "teacher");

            tx.success();
        }
        graphDb.shutdown();
    }
}
