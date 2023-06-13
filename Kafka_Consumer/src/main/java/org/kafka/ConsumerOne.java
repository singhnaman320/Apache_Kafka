package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConsumerOne {
    public static void main(String[] args) {

        /* []=-=-= Creating Logger =-=-=[] */

        Logger logger = LoggerFactory.getLogger(ConsumerOne.class.getName());

        String bootstrapServers = "localhost:9092";
        String groupId = "secApp";
        String topic = "TopicOne";

        // Creating consumer properties

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    }
}