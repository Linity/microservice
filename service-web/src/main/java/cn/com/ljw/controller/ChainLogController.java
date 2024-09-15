package cn.com.ljw.controller;

import cn.com.ljw.entity.ChainLogEntity;
import cn.com.ljw.service.ChainLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Steph_Lin on 2020/3/18.
 */
@Api(tags = "后端链上日志操作")
@RestController
@RequestMapping("/log")
public class ChainLogController {

    private static Logger logger = LoggerFactory.getLogger(ChainLogController.class);

    @Autowired
    private ChainLogService chainLogService;

    @ApiOperation("日志上链")
    @PostMapping("/saveChainLog")
    public boolean saveChainLog(@RequestBody ChainLogEntity chainLogEntity) {
        return chainLogService.insertChainLog(chainLogEntity);
    }

}
