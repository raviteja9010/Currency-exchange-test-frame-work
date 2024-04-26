package com.example.steps;

import com.example.api.APIClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.InputStream;
import java.util.Map;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class APITestSteps {

    private Response response;

    @Given("^the API call is successful$")
    public void theAPICallIsSuccessful() {
        response = APIClient.getLatestRates();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("^the USD price against AED is in range of 3.6 to 3.7$")
    public void verifyUSDPriceAgainstAED() {
        Float usdToAedRate = response.jsonPath().get("rates.AED");
        Assert.assertTrue("USD to AED rate is not within expected range", usdToAedRate >= 3.6 && usdToAedRate <= 3.7);
    }

    @Then("^the API response time is not less than 3 seconds$")
    public void verifyAPIResponseTime() {
        long responseTime = response.getTime();
        Assert.assertTrue("API response time is less than 3 seconds", responseTime >= 3000);
    }

    @Then("^verify that 162 currency pairs are returned by the API$")
    public void verifyCurrencyPairCount() {
        Map<String, Float> rates = response.jsonPath().get("rates");
       Assert.assertEquals(162, rates.size());
    }

    @Then("^the API response matches the JSON schema$")
    public void verifyJSONSchema() {
        InputStream schemaStream = getClass().getResourceAsStream("/json-schema.json");
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject responseJson = new JSONObject(response.asString());
        schema.validate(responseJson);
    }

    @Then("^verify if the API response contains valid prices$")
    public void verifyValidPrice() {
        Map<String, Float> rates = response.jsonPath().get("rates");
        for (Float rate : rates.values()) {
            Assert.assertTrue("Invalid price: " + rate, rate > 0);
        }
    }

    @Then("^check the status code and status returned by the API response$")
    public void verifyStatusCodeAndStatus() {
        String result = response.jsonPath().getString("result");
        Assert.assertEquals("success", result.toLowerCase());
        String provider = response.jsonPath().getString("provider");
        Assert.assertNotNull("Provider is null", provider);
        String documentation = response.jsonPath().getString("documentation");
        Assert.assertNotNull("Documentation URL is null", documentation);
        String termsOfUse = response.jsonPath().getString("terms_of_use");
        Assert.assertNotNull("Terms of use URL is null", termsOfUse);
        String timeLastUpdateUtc = response.jsonPath().getString("time_last_update_utc");
        Assert.assertNotNull("Last update time is null", timeLastUpdateUtc);
        String timeNextUpdateUtc = response.jsonPath().getString("time_next_update_utc");
        Assert.assertNotNull("Next update time is null", timeNextUpdateUtc);
        String baseCode = response.jsonPath().getString("base_code");
        Assert.assertEquals("USD", baseCode);
    }

    @Then("^verify that the API response contains valid rates for all currency pairs$")
    public void verifyValidRatesForAllCurrencyPairs() {
        Map<String, Float> rates = response.jsonPath().get("rates");
        for (Float rate : rates.values()) {
            Assert.assertTrue("Invalid rate: " + rate, rate > 0);
        }
    }

    @Then("^verify if the API response contains necessary provider information$")
    public void verifyProviderInformation() {
        String provider = response.jsonPath().getString("provider");
        Assert.assertNotNull("Provider is null", provider);
    }

    @Then("^verify if the API response contains valid documentation URL$")
    public void verifyDocumentationURL() {
        String documentation = response.jsonPath().getString("documentation");
        Assert.assertNotNull("Documentation URL is null", documentation);
    }

    // Additional acceptance criteria
    // Add step definitions for other acceptance criteria as needed
}
