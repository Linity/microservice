package cn.com.ljw.task;

import cn.com.ljw.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steph_Lin
 * @date 2020/11/20
 */
@Service
public class PushTask {

    private static Logger logger = LoggerFactory.getLogger(PushTask.class);

    @Autowired
    private WebSocket webSocket;

    /**
     * 每隔1分钟
     */
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0/1 * * * ?")
    public void pushMessage() {
        logger.info("start push...");
        List<String> result = new ArrayList<>();
        result.add("1");
        result.add("2");
        result.add("3");
        webSocket.sendObjMessage("1", result);
        logger.info("end push...");
    }

}
