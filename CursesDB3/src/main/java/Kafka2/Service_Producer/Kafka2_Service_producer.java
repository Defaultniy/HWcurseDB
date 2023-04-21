package Kafka2.Service_Producer;

import Kafka2.Domain.Order_domain;
import Kafka2.Producer.Kafka2_Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Kafka2_Service_producer {

    private final Kafka2_Producer kafka2Producer;

    @Autowired
    public Kafka2_Service_producer(Kafka2_Producer kafka2Producer) {
        this.kafka2Producer = kafka2Producer;
    }


    public String createOrder(Order_domain Order_domain) throws JsonProcessingException {
        return kafka2Producer.sendMessage(Order_domain);
    }
}
