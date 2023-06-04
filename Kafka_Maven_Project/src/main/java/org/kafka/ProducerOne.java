package org.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/*
    1. bootstrap.servers: It is a list of the port pairs which are used for establishing an initial connection to the
       Kafka cluster. The users can use the bootstrap servers only for making an initial connection only. This server
       is present in the host:port, host:port,... form.

   
*/
public class ProducerOne {
    public static void main(String[] args) {

        // Creating properties

        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    }
}
