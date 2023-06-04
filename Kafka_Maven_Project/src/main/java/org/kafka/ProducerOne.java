package org.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/*
    1. bootstrap.servers: It is a list of the port pairs which are used for establishing an initial connection to the
       Kafka cluster. The users can use the bootstrap servers only for making an initial connection only. This server
       is present in the host:port, host:port,... form.

    2. key.serializer: It is a type of Serializer class of the key which is used to implement the 'org.apache.kafka.
       common.serialization.Serializer' interface.

    3. value.serializer: It is a type of Serializer class which implements the 'org.apache.kafka.common.serialization.
       Serializer' interface.
*/
public class ProducerOne {
    public static void main(String[] args) {

        // Creating properties
        // When we create the properties, it imports the 'java.util.Properties' to the code.

        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Creating Producer
        
    }
}
