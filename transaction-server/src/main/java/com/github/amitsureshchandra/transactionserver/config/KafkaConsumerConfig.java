package com.github.amitsureshchandra.transactionserver.config;

import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionListDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put("group.id", "my-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", "org.apache.kafka.common.serialization.JavaDeserializer");
        props.put("specific.avro.reader", true);
        props.put("deserializer.trusted.packages", "com.github.amitsureshchandra.common.dto.transaction");

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        return props;
    }

    @Bean
    public ConsumerFactory<String, DistributedTransactionListDto> consumerFactory() {
        JsonDeserializer<DistributedTransactionListDto> deserializer = new JsonDeserializer<>(DistributedTransactionListDto.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deseria lizer.addTrustedPackages("com.github.amitsureshchandra.common.dto");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DistributedTransactionListDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DistributedTransactionListDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
