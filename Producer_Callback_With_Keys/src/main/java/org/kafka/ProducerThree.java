package org.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/*
    []=-=-=-=- Producer with Keys =-=-=-=-[]
    Keys become useful when a user wants to send the message to the same partition. In order to send the data, the user
    need to specify a key. The key will uniquely identify the partition from the other partitions. The user needs to
    send synchronous messages to the Kafka.
*/
public class ProducerThree {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final Logger logger = LoggerFactory.getLogger(ProducerThree.class);
        // []=-=-= Creating properties =-=-=[]

        String bootstrapServers = "localhost:9092";  // or localhost:9092
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());



        KafkaProducer<String, String> firstProducer = new KafkaProducer<>(properties); // []=-=-= Creating Producer =-=-=[]

        for (int i=0 ; i<10 ; i++){

            String topic = "TopicOne";
            String value = "Produced Data" + Integer.toString(i);
            String key = "GivenKey" + Integer.toString(i);

            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(topic,key,value);  // []=-=-= Creating the Producer Record =-=-=[]

            logger.info("key" + key);

            // []=-=-= Sending data with Kafka Producer Callbacks =-=-=[]

            firstProducer.send(producerRecord, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception exception) {

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
            }).get();  // sending synchronous data forcefully
        }

        firstProducer.flush(); // Common in both sending methods
        firstProducer.close(); // Common in both sending methods

        /*

        We have specified the topic name, its value, and the key. While creating the ProducerRecord, three of them are
        passed as the parameters. If the exception 'exception' will be equal to null, the logger will fetch the information
        about the key. At the end, a get() function is used when the data is sent to the Kafka. This method sends the
        data synchronously and forcefully. The users can try their own ways to implement the keys.

        Note: Using the get(), a red underline will appear. Press alt+enter, it will say to 'Add exceptions to method
        signature', select it. This will add two exceptions to the main(), as shown. Also, it will import
        'java.util.concurrent.ExecutionException' to the code.

        The message 'Produced Data' will always go to the specified partitions now.

        */
    }
}
