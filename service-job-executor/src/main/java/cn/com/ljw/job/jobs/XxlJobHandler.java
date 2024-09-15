package cn.com.ljw.job.jobs;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author Steph_Lin
 * @date 2024/9/12
 */
@Component
public class XxlJobHandler {

    @XxlJob(value = "test")
    public void test() {
        System.out.println("job work...");
    }

}
