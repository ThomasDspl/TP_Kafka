package org.kafka.tp.producer;


import org.apache.kafka.clients.producer.Producer;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.TimerTask;

public class ProducerUn extends TimerTask {

    private Producer<String, String> producer;
    private Client clientWeb;
    private JSONParser parser;
    private static final String API_URI = "https://api.covid19api.com/summary";

    public ProducerUn(){
        producer = ProducerFactory.createProducer();
        clientWeb = ClientBuilder.newClient();
        parser = new JSONParser();
    }

    public void run() {
        Response r = clientWeb.target(API_URI).request(MediaType.APPLICATION_JSON).get();
        String jsonString = r.readEntity(String.class);

        try {
            JSONObject json = (JSONObject) parser.parse(jsonString);
            System.out.println(json.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Running: " + new java.util.Date());
//            }
//        }, 0, 1000); //180000 = 30 min

        //boucle infini
//        while (!Thread.interrupted()){
//
//        }
    }


}
