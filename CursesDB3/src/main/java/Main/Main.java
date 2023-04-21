package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Instant;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Main {

    public static void main(String[] args){
        /*ConfigurableApplicationContext run =
                SpringApplication.run(Main.class, args);

        var bean = run.getBean(MeasurementRepository.class);
        bean.save(new Measurements(Instant.now(), "IED", 1.0));

        System.out.println(bean.findAll());*/
        SpringApplication.run(Main.class, args);
    }
}