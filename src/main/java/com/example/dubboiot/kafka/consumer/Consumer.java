//package com.example.dubboiot.kafka.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * description: Consumer <br>
// * date: 2020/1/3 11:28 <br>
// * author: EDZ <br>
// * version: 1.0 <br>
// */
//@Component
//@Slf4j
//public class Consumer {
//
//    @KafkaListener(topics = {"test"})
//    public void listen(ConsumerRecord<?, ?> record) {
//
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//
//        if (kafkaMessage.isPresent()) {
//
//            Object message = kafkaMessage.get();
//
//            log.info("----------------- record =" + record);
//            log.info("------------------ message =" + message);
//        }
//
//    }
//}
