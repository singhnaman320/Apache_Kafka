package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConsumerOne {
    public static void main(String[] args) {

        /* []=-=-= Creating Logger =-=-=[] */

        Logger logger = LoggerFactory.getLogger(ConsumerOne.class.getName());

        String bootstrapServers = "localhost:9092";
        String groupId = "secApp";
        String topic = "TopicOne";

        // []=-=-= Creating consumer properties =-=-=[]

        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // []=-=-= Creating Consumer =-=-=[]

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        /* []=-=-= Subscribing the consumer to Topics =-=-=[]

        Here, we have used Arrays.asList() because may be the user wants to subscribe either to one or
        multiple topics.

         */

        consumer.subscribe(List.of(topic));

        // []=-=-= Polling for new data: The consumer reads data from Kafka through the polling method. =-=-=[]

        while (true){

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records){

                logger.info("Key: " + record.key() + ", Value: " + record.value());
                logger.info("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}