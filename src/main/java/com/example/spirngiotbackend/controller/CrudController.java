package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.model.Device;
import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.model.RegisterDto;
import com.example.spirngiotbackend.repository.DeviceRepository;
import com.example.spirngiotbackend.service.RecordService;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CrudController {
    private final RecordService recordService;
    private final DeviceRepository deviceRepository;
    private MongoCollection<Document> collection;
    private MongoDatabase database;
    MongoClient mongoClient;



    private void connectToDb(){
        mongoClient = MongoClients.create("mongodb+srv://esp_admin:dupa1234@mongo-storage.7zu4pph.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("esp32metrics");
        collection = database.getCollection("record");
    }
    @Autowired
    public CrudController(RecordService recordService, DeviceRepository deviceRepository) {
        this.recordService = recordService;
        this.deviceRepository = deviceRepository;
        connectToDb();
    }
    @PostMapping("api/records")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void addRecord(@RequestBody Record record){
        recordService.addRecord(record);
    }
    @GetMapping("api/records")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Record> getAllRecords(){
        return recordService.getAllRecords();
    }

    @GetMapping("api/record/{username}/{deviceId}")
    @PreAuthorize("hasRole('USER')")
    public Integer getRecordForUser(@PathVariable String username, @PathVariable String deviceId){
        // get the records belonging to the user
        // sort the records and return record with highest 'timestamp'
        System.out.println("getting records");
        Document filter = new Document("userId", " "+username);
        Bson bson = collection.find(filter).sort(new Document("timestamp", -1)).first();
        assert bson != null;
        System.out.println(bson.toBsonDocument().get("temp").asInt32().getValue());
        return bson.toBsonDocument().get("temp").asInt32().getValue();

//        String username1 = " " + username;
//        System.out.println(username1);
//        List<Record> records = recordService.getAllRecords();
//        List<Record> record_sorted;
//        // sort records by object id
//        record_sorted = records.stream().filter(record -> record.getUserId().equals(username1)).collect(Collectors.toList());
//
//        Record topRecord = record_sorted.get(0);
//        for (Record record : record_sorted) {
//            if(record.getTimestampAsDate().after(topRecord.getTimestampAsDate())){
//                topRecord = record;
//            }
//        }
//        return topRecord;
    }

    //
    //@GetMapping("api/record/test/{username}")
    public List<Record> testGetALlrecordsForCertainUser(@PathVariable String username){
        return recordService.getAllRecordsForUser(username);
    }

    @PostMapping("api/devices")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void registerDeviceForUser(@RequestBody RegisterDto registerDto){
        Device device = new Device(registerDto.getDeviceId(), registerDto.getUsername());
        deviceRepository.save(device);

    }
}
