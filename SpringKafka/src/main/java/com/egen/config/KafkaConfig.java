package com.egen.config;

import com.egen.entity.ItemQuantity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, ItemQuantity> consumerFactory() {
        // HashMap to store the Kafka configurations
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"); //localhost
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "id");

        // return message in JSON format
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(ItemQuantity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ItemQuantity> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ItemQuantity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory((consumerFactory()));
        factory.setMissingTopicsFatal(false);
        return factory;
    }
}