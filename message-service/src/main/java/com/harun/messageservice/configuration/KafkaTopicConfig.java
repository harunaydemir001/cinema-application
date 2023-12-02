package com.harun.messageservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic javaGuidesTopic(){
        return TopicBuilder.name("javaguides").build();
    }

    @Bean
    public NewTopic javaGuidesJsonTopic(){
        return TopicBuilder.name("javaguides_json").build();
    }
}
