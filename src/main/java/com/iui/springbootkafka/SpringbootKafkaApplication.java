package com.iui.springbootkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iui.springbootkafka.config.KafkaConfigProps;
import com.iui.springbootkafka.domain.CustomerVisitEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class SpringbootKafkaApplication {

    //Variable used to convert message into JSON format
    @Autowired
    private  ObjectMapper objectMapper;
    public static void main(String[] args) {

        SpringApplication.run(SpringbootKafkaApplication.class, args);
    }

    //Creating a message for the topic with a random customer id and the current time (producer)
    @Bean
    public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate, final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
        final CustomerVisitEvent event = CustomerVisitEvent.builder()
                        .customerId(UUID.randomUUID().toString())
                                .dateTime(LocalDateTime.now())
                                        .build();

        final String payload = objectMapper.writeValueAsString(event);

        //returns the message in the form of a function because it is required as such by ApplicationRunner
        return args -> {
            kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        };
    }

    //Creating a consumer
    @KafkaListener(topics = "KafkaBroker")
    public void listens(final String in){
        System.out.println(in);
    }
}
