package org.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerOne {
    public static void main(String[] args) {

        /* []=-=-= Creating Logger =-=-=[] */

        Logger logger = LoggerFactory.getLogger(ConsumerOne.class.getName());

        String bootstrapServers = "localhost:9092";
        String groupId = "secApp";
        String topic = "TopicOne";
    }
}