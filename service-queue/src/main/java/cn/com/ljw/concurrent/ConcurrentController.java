package cn.com.ljw.concurrent;

import org.springframework.web.bind.annotation.*;

/**
 * @author Steph_Lin
 * @date 2020/10/19
 */
@RestController
@RequestMapping(value = "/concurrent")
public class ConcurrentController {


    private static final String s1 = "test1";

    private static Integer s2 = 0;

    private Integer s3 = 0;

    @GetMapping("/test")
    public void sendQueue() {
        System.out.println(s2 + 1);
        System.out.println(s3 + 1);
    }

}
