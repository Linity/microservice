package cn.com.ljw.contract.service;

import cn.com.ljw.contract.config.ContractConfig;
import cn.hyperchain.sdk.crypto.ECPriv;
import cn.hyperchain.sdk.exception.TxException;
import cn.hyperchain.sdk.rpc.Transaction.Transaction;
import cn.hyperchain.sdk.rpc.function.FuncParamReal;
import cn.hyperchain.sdk.rpc.function.FunctionEncode;
import cn.hyperchain.sdk.rpc.returns.ReceiptReturn;
import cn.hyperchain.sdk.rpc.utils.ByteUtil;
import cn.hyperchain.sdk.rpc.utils.Utils;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Steph_Lin on 2020/3/24.
 */
@Component
public class ChainService {

    private Logger logger = Logger.getLogger(ChainService.class);

    private ContractConfig contractConfig;

    @Autowired
    public ChainService(ContractConfig contractConfig){
        this.contractConfig = contractConfig;
        this.contractConfig.init();
    }
//    private static ContractConfig contractConfig = null;
//
//    public synchronized static ECPriv getAccount(){
//        if(contractConfig == null){
//            contractConfig = new ContractConfig();
//        }
//        return contractConfig.getDefaultAccount();
//    }

    // 部署合约
    public String deployContract(){
        try {
            String bin = Utils.readFile("logContract_sol_LogContract.bin").trim();
            Transaction deployTransaction = new Transaction(contractConfig.getDefaultAccount().address(), bin, false);
            // 对部署交易进行签名
            deployTransaction.sign(contractConfig.getDefaultAccount());
            // 部署合约
            ReceiptReturn receiptReturn = ContractConfig.getHyperchainAPI().deployContract(deployTransaction);
            // 查询部署结果, 取得合约地址
            String contractAddress;
            if (receiptReturn.isSuccess()){
                contractAddress = receiptReturn.getContractAddress();
                return contractAddress;
            }else {
                System.out.println("错误原因 : " + receiptReturn.getError());
                throw new RuntimeException("Contract deploy failed!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateContract(){
        try {
            String bin = Utils.readFile("logContract_sol_LogContract.bin").trim();
            Transaction deployTransaction = new Transaction(contractConfig.getDefaultAccount().address(), bin, false);
            // 对部署交易进行签名
            deployTransaction.sign(contractConfig.getDefaultAccount());
            // 部署合约
            ReceiptReturn receiptReturn = ContractConfig.getHyperchainAPI().maintainContract(deployTransaction);
            // 查询部署结果, 取得合约地址
            String contractAddress;
            if (receiptReturn.isSuccess()){
                contractAddress = receiptReturn.getContractAddress();
                return contractAddress;
            }else {
                System.out.println("错误原因 : " + receiptReturn.getError());
                throw new RuntimeException("Contract update failed!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 执行合约方法
    public <T> T invoke(String contractAddress, String function, Class<T> type, FuncParamReal... funcParams){
        // 取得编码方法体
        String invokePayload = FunctionEncode.encodeFunction(function, funcParams);
        // 构造合约调用transaction
        try {
            Transaction invokeTransaction = new Transaction(contractConfig.getDefaultAccount().address(), contractAddress, invokePayload, false);
            // 7. 对合约调用transaction 进行签名
            invokeTransaction.sign(contractConfig.getDefaultAccount());
            // 8. 取得abi 注意需要trim
            String abi = Utils.readFile("logContract_sol_LogContract.abi").trim();
            // 9. 调用合约
            ReceiptReturn invokeResult = ContractConfig.getHyperchainAPI().invokeContract(invokeTransaction, "add", abi);
            if (invokeResult.isSuccess()) {
                logger.info("invoke Success");
                // TODO change here no need to decode
                String result = new String(ByteUtil.hexStringToBytes(invokeResult.getRet()));
                return new Gson().fromJson(result, type);
            }
        } catch (TxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
