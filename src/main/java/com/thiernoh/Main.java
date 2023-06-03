package com.thiernoh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;


@SpringBootApplication(scanBasePackages = "com.thiernoh")
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        System.out.println("Hello World!");

        String[] allBeans = applicationContext.getBeanDefinitionNames();
        for (String beanName: allBeans) {
            System.out.println(beanName);
        }
    }
}
