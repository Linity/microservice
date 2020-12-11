package cn.com.ljw.contract.java.logic;

import cn.com.ljw.contract.java.logic.entity.ContractChainLog;
import cn.hyperchain.annotations.StoreField;
import cn.hyperchain.contract.BaseContract;
import cn.hyperchain.core.HyperList;
import cn.hyperchain.core.HyperMap;
import cn.hyperchain.core.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Steph_Lin on 2020/3/13.
 */
public class ChainLogContract extends BaseContract implements IChainLog {

    private Logger logger = Logger.getLogger(ChainLogContract.class);

    @StoreField(type = "HyperMap")
    private Map<String, ContractChainLog> logs = new HyperMap<>();
    @StoreField
    private List<String> ids = new HyperList<>();

    @Override
    public Boolean registerLog(List<ContractChainLog> logs) {
        for (ContractChainLog log : logs) {
            String id = log.getId();
            // check whether the id exists
            if (this.logs.containsKey(id)) {
                return false;
            } else {
                this.logs.put(id, log);
                ids.add(id);
            }
        }
        return true;
    }

    @Override
    public List<ContractChainLog> getSupChainLogs(List<String> ids) {
        List<ContractChainLog> logs = new ArrayList<>(ids.size());
        for (String id : ids) {
            ContractChainLog log = this.logs.get(id);
            logs.add(log);
        }
        return logs;
    }

    @Override
    public void onCreated() {
        logger.debug("HOOK: contract create");
    }

    @Override
    public void onPreCommit() {
        logger.debug("HOOK: contract preCommit");
    }

    @Override
    public void onCommitted() {
        logger.debug("HOOK: contract onCommit");
    }

}
