import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ExchangeRateApiClient {
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";
    private OkHttpClient client;
    private ObjectMapper mapper;

    public ExchangeRateApiClient() {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public ExchangeRate getExchangeRates() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API call failed with status code " + response.code());
            }

            String responseBody = response.body().string();
            JsonNode jsonNode = mapper.readTree(responseBody);
            JsonNode ratesNode = jsonNode.get("rates");

            double aedRate = ratesNode.get("AED").doubleValue();
            if (aedRate < 3.6 || aedRate > 3.7) {
                throw new IOException("AED rate is not within the expected range (3.6-3.7)");
            }

            long responseTime = System.currentTimeMillis() / 1000;
            if (responseTime - jsonNode.get("time_last_update_unix").longValue() < 3) {
                throw new IOException("API response time is less than 3 seconds");
            }

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setBaseCode(jsonNode.get("base_code").textValue());
            exchangeRate.setRates(new ExchangeRates());
            for (Iterator<String> it = ratesNode.fieldNames(); it.hasNext(); ) {
                String currencyCode = it.next();
                double rate = ratesNode.get(currencyCode).doubleValue();
                exchangeRate.getRates().put(currencyCode, rate);
            }
            exchangeRate.setTimeLastUpdateUnix(jsonNode.get("time_last_update_unix").longValue());
            exchangeRate.setTimeNextUpdateUnix(jsonNode.get("time_next_update_unix").longValue());

            return exchangeRate;
        }
    }
}