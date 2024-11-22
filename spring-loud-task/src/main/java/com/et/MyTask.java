package com.et;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, BatchAutoConfiguration.class})
public class MyTask implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyTask.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // some code
        System.out.println("Executing custom task...");
        // add your logic
    }
}