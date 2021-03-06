package com.kodilla.restaurantbackend.controller;

import com.kodilla.restaurantbackend.domain.CreatedRateDto;
import com.kodilla.restaurantbackend.domain.ExchangeRatesLatestDto;
import com.kodilla.restaurantbackend.domain.RatesDto;
import com.kodilla.restaurantbackend.fasade.ExchangeRateFasade;
import com.kodilla.restaurantbackend.service.AmountCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/exchangeRates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExchangeRatesControllerDto {
    @Autowired
    private final ExchangeRateFasade exchangeRateFasade;
    @Autowired
    private final AmountCalculationService amountCalculationService;

    @RequestMapping(method = RequestMethod.GET, value = "/getLatestRates")
    public ExchangeRatesLatestDto getLatestExchangeRates() {

        ExchangeRatesLatestDto latest = exchangeRateFasade.fetchExchangeRatesLatest();
        System.out.println("base currency is " + latest.getBase() + " " + "from date " + latest.getDate());
        System.out.println("This exchange rates contains currencies: ");
        System.out.println("GBP" + " " + latest.getRatesDto().getGbp() + " " + "PLN " + " " + latest.getRatesDto().getPln()
                + " " + "USD " + " " + latest.getRatesDto().getUsd());
        return latest;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createRate")
    public CreatedRateDto createRate(@RequestBody RatesDto ratesDto) {
        return exchangeRateFasade.createRate(ratesDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromEURtoUSD")
    public Double calculateFromEURtoUSD(@RequestParam Double amount) {
        Double valueInUSD = amountCalculationService.calculateAmountFromEURToUSD(amount);
        return valueInUSD;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromEURtoPLN")
    public Double calculateFromEURtoPLN(@RequestParam Double amount) {
        Double valueInPLN = amountCalculationService.calculateAmountFromEURToPLN(amount);
        return valueInPLN;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromUSDtoEUR")
    public Double calculateFromUSDtoEUR(@RequestParam Double amount) {
        Double valueInUSD = amountCalculationService.calculateAmountFromUSDToEUR(amount);
        return valueInUSD;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromPLNtoEUR")
    public Double calculateFromPLNtoEUR(@RequestParam Double amount) {
        Double valueInPLN = amountCalculationService.calculateAmountFromPLNToEUR(amount);
        return valueInPLN;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromEURtoGBP")
    public Double calculateFromEURtoGBP(@RequestParam Double amount) {
        Double valueInEUR = amountCalculationService.calculateAmountFromEURToGBP(amount);
        return valueInEUR;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/calculateFromGBPtoEUR")
    public Double calculateFromGBPtoEUR(@RequestParam Double amount) {
        Double valueInGBP = amountCalculationService.calculateAmountFromGBPToEUR(amount);
        return valueInGBP;
    }
}

