package dev.lxqptr.debitcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DebitCardServiceApplication {

    public static void main(
            final String[] args
    ) {
        SpringApplication.run(DebitCardServiceApplication.class, args);
    }

}
