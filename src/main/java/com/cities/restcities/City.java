package com.cities.restcities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City
{
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private int medianHomePrice;
    private int affordabilityIndex;

    public City()
    {
    }

    public City(String name, int medianHomePrice, int affordabilityIndex)
    {
        this.name = name;
        this.medianHomePrice = medianHomePrice;
        this.affordabilityIndex = affordabilityIndex;
    }
}
