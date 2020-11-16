package org.kafka.tp.consumer_producter_client;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kafka.tp.consumer.ConsumerFactory;
import org.kafka.tp.database.DatabaseConnection;

import java.util.Collections;

public class ConsumerUn implements Runnable{

    private Consumer<String, String> consumer;
    private static String topic ;
    private DatabaseConnection database;
    private JSONParser jsonParser;

    public ConsumerUn(){
        consumer = new ConsumerFactory().createConsumer();
        topic = "Topic1";
        consumer.subscribe(Collections.singletonList(topic));
        database = new DatabaseConnection();
        jsonParser = new JSONParser();
    }

    @Override
    public void run() {
        database.connect();
        while(!Thread.interrupted()){
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(stringStringConsumerRecord -> {
                String s = stringStringConsumerRecord.value();
                try {
                    JSONObject json = (JSONObject)jsonParser.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //TODO DEBUG
                System.out.println(s);
            });
        }
        consumer.close();
    }
}
