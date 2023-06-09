package org.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerTwo {
    public static void main(String[] args) {

        // []=-=-= Creating properties =-=-=[]

        String bootstrapServers = "localhost:9092";  // or localhost:9092
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // []=-=-= Creating Producer =-=-=[]

        KafkaProducer<String, String> firstProducer = new KafkaProducer<>(properties);

        // []=-=-= Creating the Producer Record =-=-=[]

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("TopicOne","data created");

        // []=-=-= Sending data with Kafka Producer Callbacks =-=-=[]

        /*
        In order to understand more deeply, i.e., whether the data was correctly produced, where it was produced, about
        its offset and partition value, etc. we will use callback functions.

        The callback function used by the producer is the onCompletion(). Basically, this method requires two arguments:

        Metadata of the Record: Metadata of the record means fetching the information regarding the partition and its
        offsets. If it is not null, an error will be thrown.

        Exception: There are following exceptions which can be thrown while processing:

        1) Retriable exception: This exception says that the message may be sent.

        2) Non-retriable exception: This exception throws the error that the message will never be sent.
        */

        firstProducer.send(producerRecord, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception exception) {

                Logger logger = LoggerFactory.getLogger(ProducerTwo.class);

                if (exception == null){

                    logger.info("Details are given as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp() +"\n");

                }else{

                    logger.error("Unable to produce the data, getting error: ",exception);
                }
            }
        });

        firstProducer.flush(); // Common in both sending methods
        firstProducer.close(); // Common in both sending methods

    }
}
