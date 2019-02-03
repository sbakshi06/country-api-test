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
public class Country {

    private String name;
    @JsonProperty("alpha2_code")
    private String alpha2Code;
    @JsonProperty("alpha3_code")
    private String alpha3Code;
}
