package Kafka2.CustomDeSerializer;

import Kafka2.Domain.Order_domain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;


@Slf4j
public class CustomSerializer  implements Serializer<Order_domain> {
    @Override
    public byte[] serialize(String s, Order_domain commandDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (commandDto != null) {
            try {
                return objectMapper.writeValueAsBytes(commandDto);
            } catch (JsonProcessingException e) {
                log.error("Unable to serialize measurement cause : {}", e.getMessage(), e);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
