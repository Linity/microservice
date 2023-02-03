package cn.com.ljw.flink.mysql;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * flink mysql cdc test
 *
 * @author Steph_Lin
 * @date 2023/1/18
 */
public class MySqlSourceExample {

    public static void main(String[] args) throws Exception {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("10.12.102.110")
                .port(3306)
                .databaseList("flink") // set captured database
                .tableList("flink.aaa") // set captured table
                .username("root")
                .password("OO9OZDUKojbpSFB26AFT0BOSEQA=")
                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
                .build();


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // enable checkpoint
        env.enableCheckpointing(3000);

        DataStreamSink<String> dataStreamSink = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                // set 4 parallel source tasks
                .setParallelism(4)
                .print().setParallelism(1);// use parallelism 1 for sink to keep message ordering

//        dataStreamSink.

//        env.execute();
        env.execute("Print MySQL Snapshot + Binlog");
    }

}
