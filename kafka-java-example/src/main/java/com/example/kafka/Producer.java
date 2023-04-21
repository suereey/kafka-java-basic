package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class Producer {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(Producer.class);

        /*
        Properties properties = new Properties();
        properties.put("client.id", InetAddress.getLocalHost().getHostName());
        properties.put("bootstrap.servers", "host1:9092,host2:9092");
        properties.put("acks", "all");
         */

        //Create properties object for Producer
        //set the properties for bootstrap servers, key and value serializers.
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create the Producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        for (int i = 0; i < 20; ++i){
            ProducerRecord<String, String> record = new ProducerRecord("example-topic", "exmaple-key", "exmaple-value: " + i);
            //Send Data - Asynchronous
            producer.send(record);
        }
        producer.close();
    }
}
