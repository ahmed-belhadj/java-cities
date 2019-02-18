package com.cities.restcities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener
{
    @RabbitListener(queues = RestCitiesApplication.QUEUE_NAME_SECRET)
    public void receiveSecretMessage(CityMessage message)
    {
        log.info("Received Secret Message: {} ", message.toString());
    }

    @RabbitListener(queues = RestCitiesApplication.QUEUE_NAME_CITY_ONE)
    public void receiveCityOneMessage(CityMessage message)
    {
        log.info("Received City 1 Message: {} ", message.toString());
    }

    @RabbitListener(queues = RestCitiesApplication.QUEUE_NAME_CITY_TWO)
    public void receiveCityTwoMessage(CityMessage message)
    {
        log.info("Received City 2 Message: {} ", message.toString());
    }
}
