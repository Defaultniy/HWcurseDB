package Kafka2.Controller;

import Kafka2.Domain.Order_domain;

import Kafka2.Service_Producer.Kafka2_Service_producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
//@RequestMapping(value = "/order")
public class Kafka2_Controller {

    private final Kafka2_Service_producer kafka2Service;

    @Autowired
    public Kafka2_Controller(Kafka2_Service_producer kafka2Service) {
        this.kafka2Service = kafka2Service;
    }

    @PostMapping(value = "/getorder")
    public String createFoodOrder(@RequestBody Order_domain Order_domain) throws JsonProcessingException {
        log.info("create order request received");
        return kafka2Service.createOrder(Order_domain);
    }

    @GetMapping(value = "/get")
    public void createvoid(@RequestParam long startIndex){
        log.info(String.valueOf(startIndex));
        log.info("Все ок");
        log.debug("Все ок");
    }

}
