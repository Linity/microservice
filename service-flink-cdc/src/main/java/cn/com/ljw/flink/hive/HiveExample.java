package cn.com.ljw.flink.hive;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.hive.HiveCatalog;

/**
 * 10.10.77.104 hadoop版本3.2.4 hive版本3.1.3
 *
 * @author Steph_Lin
 * @date 2023/2/3
 */
public class HiveExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        tableEnv.executeSql("CREATE TABLE aaa_kafka (" +
                " `id` DOUBLE," +
                " `name` STRING," +
                " `number` INT" +
                " ) WITH (" +
                " 'connector'='kafka'," +
                " 'topic'='test'," +
                " 'properties.bootstrap.servers'='10.10.77.107:9092'," +
                " 'properties.group.id'='group'," +
                " 'sink.partitioner'='round-robin'," +
                " 'sink.parallelism'='1'," +
                " 'format'='json'" +
                ")");

        // 设置操作用户 避免权限问题
        System.setProperty("HADOOP_USER_NAME", "root");

        String createTable = "create table IF NOT EXISTS aaa_copy\n" +
                "(\n" +
                "    id bigint,\n" +
                "    name string,\n" +
                "    number int\n" +
                ")";
        tableEnv.getConfig().setSqlDialect(SqlDialect.HIVE);

        String name            = "HiveCatalogName";
        String defaultDatabase = "hive_test";
        String hiveConfDir     = "/ljw/hive-conf";

        HiveCatalog hive = new HiveCatalog(name, defaultDatabase, hiveConfDir);
        tableEnv.registerCatalog(name, hive);

        // set the HiveCatalog as the current catalog of the session
        tableEnv.useCatalog(name);
        tableEnv.executeSql(createTable);

        // 执行逻辑，需要提前创建好hive的库表。
        tableEnv.executeSql("INSERT INTO table HiveCatalogName.hive_test.aaa_copy SELECT id, name, number FROM " +
                "default_catalog.default_database.aaa_kafka").print();
//        tableEnv.executeSql("show tables").print();
        tableEnv.executeSql("select * from hive_test.aaa_copy").print();

        env.execute();
    }

}
