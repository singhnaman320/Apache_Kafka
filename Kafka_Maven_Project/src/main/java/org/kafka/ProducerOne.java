package org.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("TopicOne","Created successfully");

        // OR

        //ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic:"TopicOne",key:"message",value:"Created successfully");
        /* Note: Create this topic manually */

        /*
        Here, 'producerRecord' is the name chosen for creating the producer record, 'TopicOne' is the topic name, and
        'Creating TopicOne' is the message. The user can choose accordingly.
        */

        // []=-=-= Sending data =-=-=[]

        firstProducer.send(producerRecord);

        //OR

        // []=-=-= Sending data with Kafka Producer Callbacks =-=-=

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

                Logger logger = LoggerFactory.getLogger(ProducerOne.class);

                if (exception == null){

                    logger.info("Details are given as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp() +"\n");

                }else{


                }
            }
        });

        firstProducer.flush(); // Common in both sending methods
        firstProducer.close(); // Common in both sending methods

        /*
        The data produced by a producer is asynchronous. Therefore, two additional functions, i.e., flush() and close()
        are required (as seen in the above snapshot). The flush() will force all the data to get produced and close()
        stops the producer. If these functions are not executed, data will never be sent to the Kafka, and the consumer
        will not be able to read it.
        */
    }
}
