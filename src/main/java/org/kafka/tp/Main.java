package org.kafka.tp;

import org.kafka.tp.consumer_producter_client.ConsumerUn;
import org.kafka.tp.consumer_producter_client.ProducerUn;

import java.util.Timer;

public class Main{

        public Main() throws InterruptedException {
            ProducerUn p = new ProducerUn();
            ConsumerUn c = new ConsumerUn();
            Timer timer = new Timer();
            //TODO Mettre à 1 800 000 pour 30 min
            timer.schedule(p,0,10000);

            Thread t = new Thread(c);
            t.run();
            while (true){

            }
        }

        public static void main(String[] args) throws InterruptedException {
            Main main = new Main();
        }
}