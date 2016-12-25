package com.dev.inhouse.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
public class SchedulerApplication
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = SpringApplication.run(SchedulerApplication.class,args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String [] beanNames = ctx.getBeanDefinitionNames();

        Arrays.sort(beanNames);

        StringBuilder builder = new StringBuilder();
        for (String beanName : beanNames) {
            builder.append(beanName + ",");
        }
        String out = builder.toString();
        out = out.substring(0,out.lastIndexOf(",")-1);

        System.out.print(out);
    }
}
