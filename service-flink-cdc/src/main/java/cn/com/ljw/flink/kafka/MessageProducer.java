package cn.com.ljw.flink.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 测试kafka生产者
 * 无法连接kafka，考虑防火墙是否关闭
 *
 * @author Steph_Lin
 * @date 2023/2/2
 */
public class MessageProducer {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private static final String TOPIC = "test";
    private static final String BROKER_LIST = "10.10.77.107:9092";
    private static KafkaProducer<String, String> producer;

    /*
    初始化生产者
     */
    static {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }

    public static void main(String[] args) {
        //消息实体
        ProducerRecord<String, String> record;
        for (int i = 0; i < 10; i++) {
            record = new ProducerRecord<>(TOPIC, "value" + (int) (10 * (Math.random())));
            //发送消息
            producer.send(record, (recordMetadata, e) -> {
                if (null != e) {
                    log.info("send error" + e.getMessage());
                } else {
                    System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                }
            });
        }
        producer.close();
    }

}
