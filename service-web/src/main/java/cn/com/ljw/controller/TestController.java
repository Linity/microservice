package cn.com.ljw.controller;

import cn.com.ljw.websocket.WebSocket;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steph_Lin on 2020/5/9.
 */
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    WebSocket webSocket;

    @GetMapping("/websocket")
    public String websocket(){

//        webSocket.sendTextMessage("1", "处理完毕！");
        List<String> result = new ArrayList<>();
        result.add("1");
        result.add("2");
        result.add("3");
        webSocket.sendObjMessage("1", result);

        return "success";
    }


    public static void main(String[] args) {

    }

}
