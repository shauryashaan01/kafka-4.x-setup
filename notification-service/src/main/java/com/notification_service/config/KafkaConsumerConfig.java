package com.notification_service.config;

import com.notification_service.constants.AppConstants;
import com.notification_service.dto.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory() {

        Map<String, Object> kafkaProps = new HashMap<>();

        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");

        JacksonJsonDeserializer<OrderEvent> deserializer = new JacksonJsonDeserializer<>(OrderEvent.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(
                kafkaProps,
                new StringDeserializer(),
                deserializer
            );
         }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
