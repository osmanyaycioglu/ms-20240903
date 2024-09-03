package org.training.turkcell.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NodeConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodeConfigurationApplication.class,
                              args);
    }

}
