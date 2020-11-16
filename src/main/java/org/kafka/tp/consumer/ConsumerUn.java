package org.kafka.tp.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.kafka.tp.database.DatabaseConnection;

import java.util.Collections;

public class ConsumerUn implements Runnable{

    private Consumer<String, String> consumer;
    private static String topic ;
    private DatabaseConnection database;

    public ConsumerUn(){
        consumer = new ConsumerFactory().createConsumer();
        topic = "Topic1";
        consumer.subscribe(Collections.singletonList(topic));
        database = new DatabaseConnection();
    }

    @Override
    public void run() {
        database.connect();
        while(!Thread.interrupted()){
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(stringStringConsumerRecord -> {
                String s = stringStringConsumerRecord.value();
                //TODO DEBUG
                System.out.println(s);
            });
        }
        consumer.close();
    }
}
