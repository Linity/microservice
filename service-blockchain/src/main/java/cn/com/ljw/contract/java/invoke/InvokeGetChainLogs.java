package cn.com.ljw.contract.java.invoke;

import cn.com.ljw.contract.java.logic.IChainLog;
import cn.com.ljw.contract.java.logic.entity.ContractChainLog;
import cn.hyperchain.contract.BaseInvoke;

import java.util.List;

/**
 * Created by Steph_Lin on 2020/3/13.
 */
public class InvokeGetChainLogs implements BaseInvoke<List<ContractChainLog>, IChainLog> {

    private List<String> ids;

    public InvokeGetChainLogs(){}

    public InvokeGetChainLogs(List<String> ids){
        this.ids = ids;
    }

    @Override
    public List<ContractChainLog> invoke(IChainLog iChainLog) {
        return iChainLog.getSupChainLogs(ids);
    }
}
