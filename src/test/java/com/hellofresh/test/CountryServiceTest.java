package com.hellofresh.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hellofresh.base.TestBase;

import com.hellofresh.model.AggregatedCountryRestResponse;
import com.hellofresh.model.Country;
import com.hellofresh.model.CountryRestResponse;
import com.hellofresh.utils.ConversionUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.hellofresh.constants.TestConstant.*;
import static io.restassured.RestAssured.given;


public class CountryServiceTest extends TestBase {

    private CountryRestResponse countryRestResponse;
    private Country newCountry;
    private String countryGetPath;
    private String allCountriesGetPath;
    private String countryPostPath;

    @BeforeTest
    public void setup() throws IOException {
        RestAssured.baseURI = PROPS.getProperty(API_BASE_URL);
        allCountriesGetPath = PROPS.getProperty(COUNTRIES_API_GET_PATH);
        countryGetPath = PROPS.getProperty(COUNTRY_API_GET_PATH);
        countryPostPath = PROPS.getProperty(COUNTRY_API_POST_PATH);
        newCountry = ConversionUtil.convertFileContentToObject("jsons/test-country.json",
                new TypeReference<Country>() {
                });
    }

    @Test
    public void validateGetAllCountriesResponse() {
        Response response = given().get(allCountriesGetPath);
        Assert.assertEquals(response.as(AggregatedCountryRestResponse.class).getAggregatedCountry().getCountries().size(), 249);
        Assert.assertTrue(response.asString().contains("\"alpha2_code\" : \"US\""));
        Assert.assertTrue(response.asString().contains("\"alpha2_code\" : \"DE\""));
        Assert.assertTrue(response.asString().contains("\"alpha2_code\" : \"GB\""));
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        LOGGER.info("Validated that response has US, DE and GB in it");
    }

    @Test
    public void validateGetCountryResponseForUS() {
        countryRestResponse = given().pathParam("countryCode", "US")
                .when().get(countryGetPath)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().body().as(CountryRestResponse.class);
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getName(), "United States of America");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha2Code(), "US");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha3Code(), "USA");
        LOGGER.info("Validated get country response for US");
    }

    @Test
    public void validateGetCountryResponseForDE() {
        countryRestResponse = given().pathParam("countryCode", "DE")
                .when().get(countryGetPath)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().body().as(CountryRestResponse.class);
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getName(), "Germany");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha2Code(), "DE");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha3Code(), "DEU");
        LOGGER.info("Validated get country response for DE");
    }

    @Test
    public void validateGetCountryResponseForGB() {
        countryRestResponse = given().pathParam("countryCode", "GB")
                .when().get(countryGetPath)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().body().as(CountryRestResponse.class);
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getName(), "United Kingdom of Great Britain and Northern Ireland");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha2Code(), "GB");
        Assert.assertEquals(countryRestResponse.getCountryResponse().getCountry().getAlpha3Code(), "GBR");
        LOGGER.info("Validated get country response for GB");
    }

    @Test
    public void validateGetCountryResponseForNonExistentCountry() {
        countryRestResponse = given().pathParam("countryCode", "FZ")
                .when().get(countryGetPath)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().body().as(CountryRestResponse.class);
        Assert.assertTrue(countryRestResponse.getCountryResponse().getMessages().contains("No matching country found for requested code [FZ]."));
        Assert.assertNull(countryRestResponse.getCountryResponse().getCountry());
        LOGGER.info("Validated response for not existent country");
    }

    @Test
    public void validatePostNewCountryRequest() {
        given().contentType(ContentType.JSON).body(newCountry).post(countryPostPath).then().statusCode(HttpStatus.SC_CREATED);
        LOGGER.info("Validated response for adding a new country");
    }
}