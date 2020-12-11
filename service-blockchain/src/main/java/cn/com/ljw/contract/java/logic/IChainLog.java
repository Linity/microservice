package cn.com.ljw.contract.java.logic;

import cn.com.ljw.contract.java.logic.entity.ContractChainLog;
import cn.hyperchain.contract.BaseContractInterface;

import java.util.List;

/**
 * Created by Steph_Lin on 2020/3/13.
 */
public interface IChainLog extends BaseContractInterface {

    Boolean registerLog(List<ContractChainLog> logs);

    List<ContractChainLog> getSupChainLogs(List<String> ids);

}
