package com.example.tests;

import com.example.api.APIClient;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class APITest {

    @Test
    public void testAPIResponseCode() {
        Response response = APIClient.getLatestRates();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testAPIResponseStatus() {
        Response response = APIClient.getLatestRates();
        String result = response.jsonPath().getString("result");
        Assert.assertNotNull("Status is null", result);
    }

    @Test
    public void testUSDPriceAgainstAED() {
        Response response = APIClient.getLatestRates();
        Float usdToAedRate = response.jsonPath().get("rates.AED");
        Assert.assertTrue("USD to AED rate is not within expected range", usdToAedRate >= 3.6 && usdToAedRate <= 3.7);
    }

    @Test
    public void testAPIResponseTime() {
        Response response = APIClient.getLatestRates();
        String timeLastUpdateUtc = response.jsonPath().getString("time_last_update_utc");
        Assert.assertNotNull("Last update time is null", timeLastUpdateUtc);

        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        long responseTimeInSeconds = Long.parseLong(timeLastUpdateUtc.split(",")[1].trim());
        Assert.assertTrue("API response time is less than 3 seconds", currentTimeInSeconds - responseTimeInSeconds >= 3);
    }

    @Test
    public void testCurrencyPairCount() {
        Response response = APIClient.getLatestRates();
        Map<String, Float> rates = response.jsonPath().get("rates");
        Assert.assertEquals(162, rates.size());
    }

    @Test
    public void testValidPrices() {
        Response response = APIClient.getLatestRates();
        Map<String, Float> rates = response.jsonPath().get("rates");
        for (Float rate : rates.values()) {
            Assert.assertTrue("Invalid price: " + rate, rate > 0);
        }
    }

    @Test
    public void testJSONSchemaValidation() {
     
        // InputStream schemaStream = getClass().getResourceAsStream("/schema.json");
        // JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        // Schema schema = SchemaLoader.load(rawSchema);
        // JSONObject responseJson = new JSONObject(response.asString());
        // schema.validate(responseJson);
    }
}
