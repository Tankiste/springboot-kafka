package com.iui.springbootkafka.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration //Tells spring that when loading configs should load from this file also
@ConfigurationProperties(prefix = "iui.kafka") //prefix used for properties inside of our config file
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class KafkaConfigProps {

    private String topic;
}
