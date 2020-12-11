package cn.com.ljw.jms.ptp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;

/**
 * @author Steph_Lin
 * @date 2020/9/11
 */
//@Service
public class QueueProducer {

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    @Scheduled(fixedDelay=3000)//每3s执行1次
    public void sendMessage(Destination destination, final String message){
//        jmsMessagingTemplate.convertAndSend(destination, message);
        jmsMessagingTemplate.convertAndSend(message);
    }
    @JmsListener(destination="out.queue")
    public void consumerMessage(String text){
        System.out.println("从out.queue队列收到的回复报文为:"+text);
    }

}
