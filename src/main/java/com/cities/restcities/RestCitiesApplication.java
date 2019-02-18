package com.cities.restcities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestCitiesApplication
{
    public static final String EXCHANGE_NAME = "CityServer";
    public static final String QUEUE_NAME_SECRET = "SecretQueue";
    public static final String QUEUE_NAME_CITY_ONE = "City1Queue";
    public static final String QUEUE_NAME_CITY_TWO = "City2Queue";

    public static void main(String[] args)
    {
        SpringApplication.run(RestCitiesApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueSecret()
    {
        return new Queue(QUEUE_NAME_SECRET);
    }

    @Bean
    public Binding declareBindingSecret()
    {
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(QUEUE_NAME_SECRET);
    }

    @Bean
    public Queue appQueueCityOne()
    {
        return new Queue(QUEUE_NAME_CITY_ONE);
    }

    @Bean
    public Binding declareBindingCityOne()
    {
        return BindingBuilder.bind(appQueueCityOne()).to(appExchange()).with(QUEUE_NAME_CITY_ONE);
    }

    @Bean
    public Queue appQueueCityTwo()
    {
        return new Queue(QUEUE_NAME_CITY_TWO);
    }

    @Bean
    public Binding declareBindingCityTwo()
    {
        return BindingBuilder.bind(appQueueCityTwo()).to(appExchange()).with(QUEUE_NAME_CITY_TWO);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
