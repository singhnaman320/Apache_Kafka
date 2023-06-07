package org.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class producerTwo {

    public static void main(String[] args) {


        // []=-=-= Creating properties =-=-=[]
        // When we create the properties, it imports the 'java.util.Properties' to the code.

        String bootstrapServers = "127.0.0.1:9092";  // or localhost:9092
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // []=-=-= Creating Producer =-=-=[]

        // To create a Kafka producer, we just need to create an object of KafkaProducer.
        // The object of KafkaProducer can be created as:

        KafkaProducer<String, String> firstProducer = new KafkaProducer<>(properties);

        // Here, 'firstProducer' is the name of the producer.

        // []=-=-= Creating the Producer Record =-=-=[]

        /* In order to send the data to Kafka, the user need to create a ProducerRecord. It is because all the producers
           lie inside a producer record. Here, the producer specifies the topic name as well as the message which is to
           be delivered to Kafka.
         */

        // A ProducerRecord can be created as:


    }
}
