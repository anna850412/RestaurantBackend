package com.kodilla.restaurantbackend.client;

import com.kodilla.restaurantbackend.config.MealConfig;
import com.kodilla.restaurantbackend.domain.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Component
@RequiredArgsConstructor
public class MealClient {
    private final MealConfig mealConfig;
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(MealClient.class);

    public MealsDto getRandomMeal() {
        URI url = getUri();
        try {

            MealsDto mealsResponse = restTemplate.getForObject(url, MealsDto.class);
            System.out.println();
            if (mealsResponse != null) {
                return  mealsResponse;
            }
            return Optional.ofNullable(mealsResponse).get();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return getRandomMeal();
        }
    }

    private URI getUri() {
        URI url = UriComponentsBuilder.fromHttpUrl(mealConfig.getMealEndPoint() + mealConfig.getMealRandom())
                .encode().build().toUri();
        return url;
    }

    public CreatedMealDto createNewMeal(MealDto mealDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(mealConfig.getMealEndPoint() + mealConfig.getMealRandom())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedMealDto.class);
    }
}
