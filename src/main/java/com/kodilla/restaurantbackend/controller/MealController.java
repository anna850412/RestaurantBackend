package com.kodilla.restaurantbackend.controller;

import com.kodilla.restaurantbackend.client.MealClient;
import com.kodilla.restaurantbackend.domain.*;
import com.kodilla.restaurantbackend.exceptions.MealNotExistException;
import com.kodilla.restaurantbackend.fasade.MealFacade;
import com.kodilla.restaurantbackend.mapper.MealMapper;
import com.kodilla.restaurantbackend.service.MealService;
import com.kodilla.restaurantbackend.service.TenDishesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final MealFacade mealFacade;
    private final MealClient mealClient;
    private final MealMapper mealMapper;
    private final TenDishesService tenDishesService;

    @GetMapping(value = "/get10Meals")
    public List<Meal> get10Meals() {
        return tenDishesService.get10Recipients();
    }

    @GetMapping(value = "/get10Categories")
    public List<String> getAllCategories() {
        return tenDishesService.getAllCategories();
    }
    @GetMapping(value = "/get10Names")
    public List<String> getNamesOfTodaysDishes() {
        return tenDishesService.getNamesOfTodaysDishes();
    }
    @GetMapping(value = "/get10Countries")
    public List<String> getNamesOfTodaysCountires() {
        return tenDishesService.getCountryNames();
    }
    @GetMapping(value = "/getAllMeals")
    public List<MealDto> getAllMeals() {

        return mealMapper.mapToMealDtoList(mealService.findAllMeals());
    }

    @GetMapping(value = "/findAllClientsMeals")
    public List<Meal> findAllClientsMeals() {
        return mealService.findAllClientsMeals();
    }

    @GetMapping(value = "/getRandomMeal")
    public MealsDto getRandomMeal() {
        MealsDto mealExternalDto = mealClient.getRandomMeal();
        return mealExternalDto;
    }

    @PostMapping(value = "/createMeal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MealDto createMeal(@RequestBody MealDto mealDto) throws MealNotExistException {
        System.out.println(mealDto);
        Meal meal = mealMapper.mapFromMealDtoToMeal(mealDto);
        mealService.saveMeal(meal);
        return mealDto;
    }


}
