package cn.com.ljw.service;

import cn.com.ljw.contract.service.ChainService;
import cn.com.ljw.entity.ChainLogEntity;
import cn.com.ljw.mapper.ChainLogMapper;
import cn.com.ljw.model.LogModel;
import cn.hyperchain.sdk.rpc.function.FuncParamReal;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Steph_Lin on 2020/3/18.
 */
@Service
@AllArgsConstructor
public class ChainLogService {

    private static final Logger logger = LoggerFactory.getLogger(ChainLogService.class);

    private final ChainLogMapper chainLogMapper;
    private final ChainService chainService;

    public boolean insertChainLog(final ChainLogEntity chainLogEntity) {
        return chainLogMapper.insert(chainLogEntity) > 0;
    }

    public boolean save(LogModel logModel) {
//        ChainLogEntity chainLog = new ChainLogEntity();
//        Date now = new Date();
////        supChainLog.setAppId(clientId);
//        chainLog.setCode(logModel.getCode());
//        chainLog.setContent(logModel.getContent());
//        chainLog.setDepartment(logModel.getDepartment());
//        chainLog.setOptTime(logModel.getOptTime());
//        chainLog.setState(false);
//        chainLog.setIp(logModel.getIp());
//        chainLog.setModuleName(logModel.getModuleName());
//        chainLog.setUserId(logModel.getUserId());
//        chainLog.setUserName(logModel.getUserName());
//        chainLog.setOptType("");
//        chainLog.setCreateTime(now);
//        chainLog.setUpdateTime(now);
        String contractAddress = chainService.deployContract();

        logger.info("contractAddress: " + contractAddress);

        FuncParamReal p1 = new FuncParamReal("string", "123");
        FuncParamReal p2 = new FuncParamReal("string", "123456");
        FuncParamReal p3 = new FuncParamReal("string", "456");
        Boolean invoke = chainService.invoke(contractAddress, "addRoomToStore", Boolean.class, p1, p2, p3);

        logger.info("result: " + invoke);

//        return chainLogMapper.save(chainLog);
        return invoke;
    }

}
