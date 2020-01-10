package com.example.dubboiot.kafka.consumer;

import com.example.dubboiot.kafka.beans.Message;
import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * description: FlinkConsumer <br>
 * date: 2020/1/3 16:07 <br>
 * author: EDZ <br>
 * version: 1.0 <br>
 */
public class FlinkConsumer {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.189.88:9092");
        props.put("zookeeper.connect", "192.168.189.88:2181");
        props.put("group.id", "test-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

//        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>("test", new SimpleStringSchema(), props);
//        //从最早开始消费
//        consumer.setStartFromEarliest();
//        DataStream<String> stream = env
//                .addSource(consumer);
//        stream.print();
//        //stream.map();
//        env.execute();

        SingleOutputStreamOperator<Message> student = env.addSource(new FlinkKafkaConsumer<>(
                "test",   //这个 kafka topic 需要和上面的工具类的 topic 一致
                new SimpleStringSchema(),
                props)).setParallelism(1)
                .map(string -> new Gson().fromJson(string, Message.class)); //，解析字符串成 student 对象

//                student.print();

//      // 首先需要指定系统时间概念为 event time
//      env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//            // 使用 Ascending 分配 时间信息和 watermark
//        student.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Message>() {
//                @Override
//                public long extractAscendingTimestamp(Message element) {
//                    return element.f1;
//                }
//            });

        student.timeWindowAll(Time.minutes(1)).apply(new AllWindowFunction<Message, List<Message>, TimeWindow>() {
            @Override
            public void apply(TimeWindow window, Iterable<Message> values, Collector<List<Message>> out) throws Exception {
                    ArrayList<Message> students = Lists.newArrayList(values);
                    for (int i = 0; i <students.size() ; i++) {
                        System.out.println("==========================Message================================");
                        System.out.println(students.get(i));
                    }
                    if (students.size() > 0) {
                    System.err.println("++++++++++++++++++++++++++++++++++++++++++++++1 分钟内收集到 student 的数据条数是：" + students.size());
                    out.collect(students);
                }
            }
        });
        env.execute("flink learning connectors kafka");
    }
}
