package cn.com.ljw.flink.hive;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * @author Steph_Lin
 * @date 2023/2/3
 */
public class HiveExample {

    public static void main(String[] args) {
//        EnvironmentSettings settings = EnvironmentSettings.inStreamingMode();
//        TableEnvironment tableEnv = TableEnvironment.create(settings);
//
//        String name            = "myhive";
//        String defaultDatabase = "mydatabase";
//        String hiveConfDir     = "/opt/hive-conf";
//
//        HiveCatalog hive = new HiveCatalog(name, defaultDatabase, hiveConfDir);
//        tableEnv.registerCatalog("myhive", hive);
//
//// set the HiveCatalog as the current catalog of the session
//        tableEnv.useCatalog("myhive");
    }

}
