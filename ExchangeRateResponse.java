import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateResponse {

    private String result;
    private String documentation;
    private String termsOfUse;
    private long timeLastUpdateUnix;
    private long timeLastUpdateUtc;
    private long timeNextUpdateUnix;
    private long timeNextUpdateUtc;
    private String baseCode;
    private Map<String, Double> rates;

    public ExchangeRateResponse() {
        this.rates = new HashMap<>();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public long getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    public void setTimeLastUpdateUnix(long timeLastUpdateUnix) {
        this.timeLastUpdateUnix = timeLastUpdateUnix;
    }

    public long getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public void setTimeLastUpdateUtc(long timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public long getTimeNextUpdateUnix() {
        return timeNextUpdateUnix;
    }

    public void setTimeNextUpdateUnix(long timeNextUpdateUnix) {
        this.timeNextUpdateUnix = timeNextUpdateUnix;
    }

    public long getTimeNextUpdateUtc() {
        return timeNextUpdateUtc;
    }

    public void setTimeNextUpdateUtc(long timeNextUpdateUtc) {
        this.timeNextUpdateUtc = timeNextUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}