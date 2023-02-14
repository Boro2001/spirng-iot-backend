package com.example.spirngiotbackend.mqtt;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.example.spirngiotbackend.model.RecordDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.example.spirngiotbackend.model.Record;

import java.util.List;


public class Topic extends AWSIotTopic {

    private MongoCollection<Document> collection;
    private MongoDatabase database;
    MongoClient mongoClient;

    public Topic(String topic, AWSIotQos qos) {
        super(topic, qos);
        connectToDb();
    }
//
//
//    public List<Record> getDataForUserAndDevice(){
//
//    }

    @Override
    public void onMessage(AWSIotMessage message) {
        try {
            RecordDTO record= parseToRecord(message.getStringPayload());
            System.out.println(record);
            Document newDocument = new Document();
            newDocument.append("id", record.getId());
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
    private RecordDTO parseToRecord(String record) throws JsonProcessingException {


        int index = record.indexOf(':');
        String id = record.substring(index + 1, record.indexOf(',', index));
        index = record.indexOf(':', index + 1);
        String deviceId = record.substring(index + 1, record.indexOf(',', index));
        index = record.indexOf(':', index + 1);
        String userId = record.substring(index + 1, record.indexOf(',', index));
        index = record.indexOf(':', index + 1);
        String temp_string = record.substring(index + 1, record.indexOf(',', index));
        int temp = Integer.parseInt(temp_string);
        index = record.indexOf(':', index + 1);
        String timestamp = record.substring(index + 1, record.indexOf('}', index));

        return new RecordDTO(id.replace("\"" ,""), deviceId.replace("\"" ,""), userId.replace("\"" ,""), temp, timestamp.replace("\"" ,""));
    }


}
