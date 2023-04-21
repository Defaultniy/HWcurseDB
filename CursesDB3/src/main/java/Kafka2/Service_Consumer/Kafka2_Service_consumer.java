package Kafka2.Service_Consumer;

import Kafka2.Domain.Order_domain;
import Kafka2.Repository.Kafka2_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class Kafka2_Service_consumer {
    private final Kafka2_Repository Kafka2_Repository;
    //private final ModelMappper ModelMappper;
    //private final ModelMapper modelMapper;

    @Autowired
    public Kafka2_Service_consumer(Kafka2_Repository kafka2Repository) {
        this.Kafka2_Repository = kafka2Repository;
    }

    public void persistOrder(Order_domain order) {
        //Order_domain Order = modelMap.map(order, Order_domain.class);
        Order_domain persistedFoodOrder = Kafka2_Repository.save(order);

        log.info("food order persisted {}", persistedFoodOrder);
    }

}
