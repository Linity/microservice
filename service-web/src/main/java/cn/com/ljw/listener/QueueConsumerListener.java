package cn.com.ljw.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Steph_Lin
 * @date 2020/12/14
 */
@Component
public class QueueConsumerListener {

    @JmsListener(destination="${spring.activemq.queue-name:queue}")
    public void readActiveQueue(Object object) {
        System.out.println("queue接受到：" + object);
//        remoteService.applyExamine(object);
    }

}
