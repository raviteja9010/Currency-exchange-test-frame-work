import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ExchangeRateApiTest {

    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient();

    @Test
    public void testApiCallSuccess() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code());
            JsonNode responseNode = objectMapper.readTree(response.body().string());
            assertFalse(responseNode.get("result").asText().equals("error"));
        }
    }

    @Test
    public void testUsdPriceAgainstAed() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JsonNode responseNode = objectMapper.readTree(response.body().string());
            double aedRate = responseNode.get("rates").get("AED").doubleValue();
            assertTrue(aedRate >= 3.6 && aedRate <= 3.7);
        }
    }

    @Test
    public void testApiResponseTime() throws IOException, InterruptedException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        long startTime = System.currentTimeMillis();

        try (Response response = client.newCall(request).execute()) {
            assertTrue(System.currentTimeMillis() - startTime >= 3000);
        }
    }

    @Test
    public void testCurrencyPairs() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JsonNode responseNode = objectMapper.readTree(response.body().string());
            JsonNode ratesNode = responseNode.get("rates");
            assertEquals(162, ratesNode.size());
        }
    }

    @Test
    public void testJsonSchema() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JsonNode responseNode = objectMapper.readTree(response.body().string());
            String schema = generateJsonSchema(responseNode);
            assertTrue(responseNode.toString().matches(schema));
        }
    }
}