package cn.com.ljw.auth.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steph_Lin
 * @date 2020/12/17
 */
@Api(tags = "登录操作")
@RestController
@RequestMapping("/index")
public class LoginController {

    public String login() {
        return null;
    }

}
