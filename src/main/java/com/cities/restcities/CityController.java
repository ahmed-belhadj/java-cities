package com.cities.restcities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
public class CityController
{
    private final RabbitTemplate rabbitTemplate;
    private final CityRepository cityRepository;

    public CityController(RabbitTemplate rabbitTemplate, CityRepository cityRepository)
    {
        this.rabbitTemplate = rabbitTemplate;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/cities/afford")
    public void getAfford()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityRepository.findAll());

        for (City c : cities)
        {
            int priority = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            log.info("Sending Message...");
            if (secret)
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getAffordabilityIndex() < 6)
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_CITY_ONE, message);

            } else
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_CITY_TWO, message);
            }
        }
    }

    @GetMapping("/cities/homes")
    public void getHomes()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityRepository.findAll());

        for (City c : cities)
        {
            int priority = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            log.info("Sending Message...");
            if (secret)
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getMedianHomePrice() > 200000)
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_CITY_ONE, message);

            } else
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_CITY_TWO, message);
            }
        }
    }

    @GetMapping("/cities/names")
    public void getNames()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityRepository.findAll());

        for (City c : cities)
        {
            int priority = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), priority, secret);

            log.info("Sending Message...");

            if (secret)
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_SECRET, message);
            } else
            {
                rabbitTemplate.convertAndSend(RestCitiesApplication.QUEUE_NAME_CITY_ONE, message);
            }
        }
    }
}
