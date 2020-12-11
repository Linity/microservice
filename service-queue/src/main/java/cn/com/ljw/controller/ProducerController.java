package cn.com.ljw.controller;

import cn.com.ljw.model.ApplicationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author Steph_Lin
 * @date 2020/10/13
 */
@RestController
public class ProducerController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @PostMapping("/queue/test")
    public String sendQueue(@RequestBody String str) {
//        this.sendMessage(this.queue, str);
        return "success";
    }

    @PostMapping("/topic/test")
    public String sendTopic(@RequestBody String str) {
//        this.sendMessage(this.topic, str);
        return "success";
    }

    @PostMapping("/test")
    public String test(@RequestBody ApplicationFormDTO applicationFormDTO) {
        this.sendMessage(this.queue, applicationFormDTO);
        return "success";
    }

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param applicationFormDTO
     */
    private void sendMessage(Destination destination, ApplicationFormDTO applicationFormDTO){
        jmsMessagingTemplate.convertAndSend(destination, applicationFormDTO);
    }

}
