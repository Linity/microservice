package cn.com.ljw.contract.java.invoke;

import cn.com.ljw.contract.java.logic.IChainLog;
import cn.com.ljw.contract.java.logic.entity.ContractChainLog;
import cn.hyperchain.contract.BaseInvoke;

import java.util.List;

/**
 * Created by Steph_Lin on 2020/3/13.
 */
public class InvokeRegisterChainLog implements BaseInvoke<Boolean, IChainLog> {

    private List<ContractChainLog> logs;

    public InvokeRegisterChainLog(){}

    public InvokeRegisterChainLog(List<ContractChainLog> logs) {
        this.logs = logs;
    }

    @Override
    public Boolean invoke(IChainLog iChainLog) {
        return iChainLog.registerLog(this.logs);
    }

}
