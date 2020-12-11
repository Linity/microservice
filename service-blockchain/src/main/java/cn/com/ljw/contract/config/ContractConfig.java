package cn.com.ljw.contract.config;

import cn.hyperchain.sdk.crypto.ECPriv;
import cn.hyperchain.sdk.rpc.HyperchainAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Steph_Lin on 2020/3/24.
 */
@Component
@PropertySource("classpath:block.properties")
public class ContractConfig {

    private Properties prop = new Properties();

    @Value("${dev.contract.name}")
    public String ContractName;

    @Value("${dev.contract.jarname}")
    public String ContractJarName;

//    @Value("${dev.contract.prop}")
//    public String contractProp;

    @Value("${dev.contract.account}")
    public String defaultAccountJson;

    @Value("${dev.contract.accountpwd}")
    public String defaultAccountJsonPwd;

    private ECPriv defaultAccount = null;

    private InputStream propReader = null;

    public static HyperchainAPI getHyperchainAPI(){
        try {
            return HyperchainAPI.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void init(){
        try {
//            propReader = ContractConfig.class.getClassLoader().getResourceAsStream(contractProp);
            defaultAccount = HyperchainAPI.decryptAccount(defaultAccountJson, defaultAccountJsonPwd);
//            prop.load(propReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param key
     * @param address
     * @param persist
     * @return
     */
    public void setContractAddress(String key, String address, boolean persist) {
        if (persist) {
            prop.setProperty(key, address);
        }
    }

    /**
     * @param key
     * @param persist_first
     * @return
     */
    public String getContractAddress(String key, boolean persist_first) {
        String val = null;
        if (persist_first) {
            val = prop.getProperty(key);
            if (val != null) {
                return val;
            }
        }
        return prop.getProperty(key);
    }

    public ECPriv getDefaultAccount(){
        return defaultAccount;
    }

    public String getContractName() {
        return ContractName;
    }

    public String getContractJarName() {
        return ContractJarName;
    }

//    public String getContractPropPath() {
//        return contractPropPath;
//    }

    public String getDefaultAccountJson() {
        return defaultAccountJson;
    }

    public String getDefaultAccountJsonPwd() {
        return defaultAccountJsonPwd;
    }

}
