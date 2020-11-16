package org.kafka.tp.consumer_producter_client;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.simple.JSONArray;
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
                    if(!json.isEmpty()){
                        JSONObject jsonGlobal = (JSONObject)json.get("Global");
                        StringBuilder sb = new StringBuilder();
                        sb.append("NewConfirmed :" + String.valueOf((long)jsonGlobal.get("NewConfirmed"))
                                +"TotalConfirmed :" + String.valueOf((long)jsonGlobal.get("TotalConfirmed"))
                                +"NewDeaths :"+ String.valueOf((long)jsonGlobal.get("NewDeaths"))
                                +"TotalDeaths :" + String.valueOf((long)jsonGlobal.get("TotalDeaths"))
                                +"NewRecovered :" +String.valueOf((long)jsonGlobal.get("NewRecovered"))
                                +"TotalRecovered :"+ String.valueOf((long)jsonGlobal.get("TotalRecovered")));
                        String requete = " ";
                        System.out.println(sb.toString());
                        JSONArray jsonCountries = (JSONArray)json.get("Countries");
                        jsonCountries.forEach(c->{
                            JSONObject jsonCourant = (JSONObject)c;
                            StringBuilder sb1 =new StringBuilder();
                            sb1.append("Country :" + (String)jsonCourant.get("Country")
                                    +"CountryCode :" + (String)jsonCourant.get("CountryCode")
                                    +"Slug :" + (String)jsonCourant.get("Slug")
                                    +"NewConfirmed :" + String.valueOf((long)jsonCourant.get("NewConfirmed"))
                                    +"TotalConfirmed :" + String.valueOf((long)jsonCourant.get("TotalConfirmed"))
                                    +"NewDeaths :"+ String.valueOf((long)jsonCourant.get("NewDeaths"))
                                    +"TotalDeaths :" + String.valueOf((long)jsonCourant.get("TotalDeaths"))
                                    +"NewRecovered :" +String.valueOf((long)jsonCourant.get("NewRecovered"))
                                    +"TotalRecovered :"+ String.valueOf((long)jsonCourant.get("TotalRecovered")));
                            System.out.println(sb1.toString());

                        });
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                //TODO DEBUG
//                System.out.println(s);
            });
        }
        consumer.close();
    }
}
