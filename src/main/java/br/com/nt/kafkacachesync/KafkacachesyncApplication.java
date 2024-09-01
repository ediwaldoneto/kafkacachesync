package br.com.nt.kafkacachesync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.nt.kafkacachesync")
public class KafkacachesyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkacachesyncApplication.class, args);
    }

}
