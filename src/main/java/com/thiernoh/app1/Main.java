package com.thiernoh.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = PersonRepository.class)
public class Main {

    public static void main(String[] args) {
        // MongoDb Admin: thiernohAdmin/MatGadi28
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        System.out.println("Hello World!");
        printAllBeansInTheApplicationContext(applicationContext);
    }

    public static void printAllBeansInTheApplicationContext(ConfigurableApplicationContext applicationContext) {
        String[] allBeans = applicationContext.getBeanDefinitionNames();
        for (String beanName: allBeans) {
            System.out.println(beanName);
        }
    }

    @Bean("Car")
    public Car getCar() {
        return new Car("Hyundai", "Venue");
    }

    record Car(String make, String model) {}
}
