package graph;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class Graph {

    public enum NodeType implements Label {
        person, Course;
    }

    public enum RelationType implements RelationshipType {
        Knows, BelongsTo;
    }


    public static void main (String[] args) throws Exception {
        Graph graph = new Graph();
        String jsonData= graph.getJsonData();
        graph.parseJsonData(jsonData);
    }

    private void parseJsonData(String jsonData) throws IOException {
        Map jsonMap = convertJsonDataToMap(jsonData);
        executeDatabaseQuery(jsonMap);

    }


    private Map convertJsonDataToMap(String jsonData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map jsonMap = mapper.readValue(jsonData, Map.class);

        return jsonMap;
    }


    private String getJsonData() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("json.data"));

    }

    private void executeDatabaseQuery(Map jsonMap) {
        File graphDbLocation = new File("/Users/AndreasAbdi/Documents/Neo4j/graph.graphdb");
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(graphDbLocation);

        String query = "WITH {json} as data\n" +
                "UNWIND data.items as q\n" +
                "MERGE (question:Question {id:q.question_id}) ON CREATE\n" +
                "  SET question.title = q.title, question.share_link = q.share_link, question.favorite_count = q.favorite_count\n" +
                "\n" +
                "MERGE (owner:User {id:q.owner.user_id}) ON CREATE SET owner.display_name = q.owner.display_name\n" +
                "MERGE (owner)-[:ASKED]->(question)\n" +
                "\n" +
                "FOREACH (tagName IN q.tags | MERGE (tag:Tag {name:tagName}) MERGE (question)-[:TAGGED]->(tag))\n" +
                "FOREACH (a IN q.answers |\n" +
                "   MERGE (question)<-[:ANSWERS]-(answer:Answer {id:a.answer_id})\n" +
                "   MERGE (answerer:User {id:a.owner.user_id}) ON CREATE SET answerer.display_name = a.owner.display_name\n" +
                "   MERGE (answer)<-[:PROVIDED]-(answerer)\n" +
                ")";
        graphDb.execute(query, singletonMap("json", jsonMap));
        graphDb.shutdown();
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
