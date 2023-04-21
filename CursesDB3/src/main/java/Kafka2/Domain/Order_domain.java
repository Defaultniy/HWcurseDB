package Kafka2.Domain;

import java.time.Instant;

public record Order_domain(Instant timestamp, String item, double amount) {
}


