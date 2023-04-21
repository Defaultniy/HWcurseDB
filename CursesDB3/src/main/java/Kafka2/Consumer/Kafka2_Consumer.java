package Kafka2.Consumer;

import Kafka2.Domain.Order_domain;
import Kafka2.Service_Consumer.Kafka2_Service_consumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Kafka2_Consumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    private final ObjectMapper objectMapper;
    private final Kafka2_Service_consumer kafka2ServiceConsumer;

    @Autowired
    public Kafka2_Consumer(ObjectMapper objectMapper, Kafka2_Service_consumer foodOrderService) {
        this.objectMapper = objectMapper;
        this.kafka2ServiceConsumer = foodOrderService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        Order_domain Order = objectMapper.readValue(message, Order_domain.class);
        kafka2ServiceConsumer.persistOrder(Order);
    }

}