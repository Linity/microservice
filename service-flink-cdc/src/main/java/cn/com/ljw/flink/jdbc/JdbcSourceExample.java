package cn.com.ljw.flink.jdbc;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * flink jdbc connector test
 *
 * @author Steph_Lin
 * @date 2023/1/31
 */
public class JdbcSourceExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        // 在 Flink SQL 中使用jdbc注册表 'aaa'
        tableEnv.executeSql("CREATE TABLE aaa (" +
                " id DOUBLE," +
                " name STRING," +
                " number INT," +
                "PRIMARY KEY(id) NOT ENFORCED" +
                ") WITH (" +
                " 'connector' = 'jdbc'," +
                " 'url' = 'jdbc:mysql://10.12.102.110:3306/flink?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true'," +
                " 'username' = 'root'," +
                " 'password' = 'OO9OZDUKojbpSFB26AFT0BOSEQA='," +
                " 'table-name' = 'aaa'" +
                ")");
        // 在 Flink SQL 中使用mysql cdc注册表 'aaaCopy'
        tableEnv.executeSql("CREATE TABLE aaaCopy (" +
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
                " 'table-name' = 'aaa_copy1'" +
                ")");

        // mysql cdc捕捉数据插入jdbc connector
        tableEnv.executeSql("INSERT INTO aaa SELECT id, name, number FROM aaaCopy").print();

        tableEnv.executeSql("SELECT * from aaa").print();

        env.execute();
    }

}
