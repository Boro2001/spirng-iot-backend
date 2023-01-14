package com.example.spirngiotbackend.mqtt;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.example.spirngiotbackend.model.Record;


public class Topic extends AWSIotTopic {

    private MongoCollection<Document> collection;
    private MongoDatabase database;
    MongoClient mongoClient;

    public Topic(String topic, AWSIotQos qos) {
        super(topic, qos);
        connectToDb();
    }


    @Override
    public void onMessage(AWSIotMessage message) {
        try {
                Record record = parseToRecord(message.getStringPayload());
            System.out.println(record.toString());
            Document newDocument = new Document();
            newDocument.append("_id", record.getId());
            newDocument.append("deviceId", record.getDeviceId());
            newDocument.append("userId", record.getUserId());
            newDocument.append("temp", record.getTemp());
            newDocument.append("timestamp", record.getTimestamp());
            collection.insertOne(newDocument);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void connectToDb(){
        mongoClient = MongoClients.create("mongodb+srv://esp_admin:dupa1234@mongo-storage.7zu4pph.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("esp32metrics");
        collection = database.getCollection("record");


    }
    private Record parseToRecord(String record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(record, Record.class);
    }


}
