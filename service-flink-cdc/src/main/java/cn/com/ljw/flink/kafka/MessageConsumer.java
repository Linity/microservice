package cn.com.ljw.flink.kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Steph_Lin
 * @date 2023/2/2
 */
public class MessageConsumer {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    private static final String TOPIC = "test";
    private static final String BROKER_LIST = "10.10.77.107:9092";
    private static KafkaConsumer<String, String> consumer;

    static {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Lists.newArrayList(TOPIC));
    }


    public static void main(String[] args) {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.offset() + "," + record.value());
            }
        }
    }

}
