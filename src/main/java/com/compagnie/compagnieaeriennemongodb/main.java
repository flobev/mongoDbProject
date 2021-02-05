/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnie.compagnieaeriennemongodb;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import org.bson.Document;


/**
 *
 * @author flo
 */
public class main {
    public static void main(String[] args) {
        
        MongoClient mongoClient;
        
        if (args.length == 0) {
            // connect to the local database server
            mongoClient = MongoClients.create();
        } else {
            mongoClient = MongoClients.create(args[0]);
        }
        
        // get handle to "mydb" database
        MongoDatabase database = mongoClient.getDatabase("mydb");
        
        // get a handle to the "test" collection
        MongoCollection<Document> collection = database.getCollection("test");
        
        // make a document and insert it
        Document doc = new Document("name", "CompagnieAerienne")
                       .append("type", "database")
                       .append("count", 1)
                       .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);
    }
}
