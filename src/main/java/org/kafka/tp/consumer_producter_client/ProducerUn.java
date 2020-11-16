package org.kafka.tp.consumer_producter_client;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.kafka.tp.producer.ProducerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.TimerTask;

public class ProducerUn extends TimerTask {

    private Producer<String, String> producer;
    private Client clientWeb;
    private final String topic;
    private static final String API_URI = "https://api.covid19api.com/summary";

    public ProducerUn() {
        producer = ProducerFactory.createProducer();
        clientWeb = ClientBuilder.newClient();
        topic = "Topic1";
    }

    public void run() {
        Response r = clientWeb.target(API_URI).request(MediaType.APPLICATION_JSON).get();
        String jsonString = r.readEntity(String.class);
        if (!jsonString.equals("")) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, jsonString);
            producer.send(record, new ProducerCallBack());
        }
    }
    private class ProducerCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null){
                e.printStackTrace();
            }
        }
    }
}




