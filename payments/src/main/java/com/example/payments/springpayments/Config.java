package com.example.payments.springpayments;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"hello","hello-world"})
@Configuration
public class Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    @Bean
    public RabbitMQReceiver receiver() {
        return new RabbitMQReceiver();
    }

    @Profile("sender")
    @Bean
    public RabbitMQSender sender() {
        return new RabbitMQSender();
    }
}