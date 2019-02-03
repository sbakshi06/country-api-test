package com.hellofresh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedCountryResponse {
    private List<String> messages;
    @JsonProperty("result")
    private List<Country> countries;
}
