package cn.com.ljw.listener;

import cn.com.ljw.model.ApplicationFormDTO;
import cn.com.ljw.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Steph_Lin
 * @date 2020/10/13
 */
@Component
public class ActiveQueueConsumerListener {

    @Autowired
    private RemoteService remoteService;

    /**
     * queue模式的消费者
     * @param applicationFormDTO
     */
    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void readActiveQueue(ApplicationFormDTO applicationFormDTO) {
        System.out.println("queue接受到：" + applicationFormDTO);
        remoteService.applyExamine(applicationFormDTO);
    }

}
