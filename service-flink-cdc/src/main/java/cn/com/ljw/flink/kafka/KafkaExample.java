package cn.com.ljw.flink.kafka;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author Steph_Lin
 * @date 2023/2/3
 */
public class KafkaExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        KafkaSource<String> source = KafkaSource.<String>builder()
//                .setBootstrapServers("10.10.77.107:9092")
//                .setTopics("test")
//                .setGroupId("group")
//                .setStartingOffsets(OffsetsInitializer.earliest())
//                .setValueOnlyDeserializer(new SimpleStringSchema())
//                .build();
//
//        env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source")
//                .setParallelism(1)
//                .print()
//                .setParallelism(1);
//
//        env.execute();

        env.setParallelism(1);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
//        // 在 Flink SQL 中使用jdbc注册表 'aaa'
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
        tableEnv.executeSql("SELECT * from aaa").print();
        tableEnv.executeSql("INSERT INTO aaa_kafka SELECT id, name, number FROM aaa").print();
        tableEnv.executeSql("SELECT * from aaa_kafka").print();

        env.execute();
    }

}
