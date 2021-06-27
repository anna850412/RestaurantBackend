package com.kodilla.restaurantbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("PLN")
    private double pln;
    @JsonProperty("USD")
    private double usd;
    @JsonProperty("GBP")
    private double gbp;

    public RatesDto(double pln, double usd, double gbp) {
        this.pln = pln;
        this.usd = usd;
        this.gbp = gbp;
    }
}
