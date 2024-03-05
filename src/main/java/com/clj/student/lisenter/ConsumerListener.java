package com.clj.student.lisenter;

import com.clj.student.model.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ConsumerListener {

//    @KafkaListener(topics = "eshop-cache", groupId = "eshop-cache-group")
////    public void listen(KafkaMessage kafkaMessage, Acknowledgment ack) {
////    public void listen(String message) {
//    public void listen(ConsumerRecord<String, String> message, Acknowledgment ack) throws JsonProcessingException {
//        log.info("message:" + message.value());
//        ObjectMapper mapper = new ObjectMapper();
//        String kafkaMessageStr = message.value();
//        KafkaMessage kafkaMessage = mapper.readValue(kafkaMessageStr, KafkaMessage.class);
//        String serviceId = kafkaMessage.getServiceId();
//        // 如果是商品信息服务
//        if ("productInfo".equals(serviceId)) {
//            //processProductInfoChangeMessage(kafkaMessage);
//        } else if ("shopInfoService".equals(serviceId)) {
//            //processShopInfoChangeMessage(kafkaMessage);
//        }
//
//        //手动提交offset
//        ack.acknowledge();
//        log.info("消费结束");
////        log.info(String.valueOf(message.value()));
//    }
}
