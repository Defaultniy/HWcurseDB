package Kafka2.CustomDeSerializer;

import Kafka2.Domain.Order_domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomDeserializer implements Deserializer<Order_domain> {
    @Override
    public Order_domain deserialize(String topic, byte[] data){
        ObjectMapper objectMapper = new ObjectMapper();
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, Order_domain.class);
        } catch (IOException exception) {
            String message = new String(data, StandardCharsets.UTF_8);
            log.error("Unable to deserialize measurement: {}", message, exception);
            return null;
        }
    }
}
