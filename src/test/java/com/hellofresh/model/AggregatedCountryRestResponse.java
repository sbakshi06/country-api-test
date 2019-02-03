package com.hellofresh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedCountryRestResponse {
    @JsonProperty("RestResponse")
    private AggregatedCountryResponse aggregatedCountry;
}
