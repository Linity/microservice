package cn.com.ljw.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Steph_Lin
 * @date 2020/10/13
 */
@Component
public class ActiveTopicConsumerListener {

    /**
     * topic模式的消费者
     * @param message
     */
    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void readActiveQueue(String message) {
        System.out.println("topic接受到：" + message);
    }

}
