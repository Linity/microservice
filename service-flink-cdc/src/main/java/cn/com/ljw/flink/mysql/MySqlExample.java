package cn.com.ljw.flink.mysql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * flink mysql cdc test
 *
 * @author Steph_Lin
 * @date 2023/1/28
 */
public class MySqlExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        tableEnv.executeSql("CREATE TABLE aaa (" +
                " id DOUBLE," +
                " name STRING," +
                " number INT," +
                "PRIMARY KEY(id) NOT ENFORCED" +
                ") WITH (" +
                " 'connector' = 'mysql-cdc'," +
                " 'hostname' = '10.12.102.110'," +
                " 'port' = '3306'," +
                " 'username' = 'root'," +
                " 'password' = 'OO9OZDUKojbpSFB26AFT0BOSEQA='," +
                " 'database-name' = 'flink'," +
                " 'table-name' = 'aaa'" +
                ")");
        tableEnv.executeSql("select * from aaa").print();
        env.execute();
    }

}
