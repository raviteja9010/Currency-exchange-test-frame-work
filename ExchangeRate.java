import java.util.HashMap;
import java.util.Map;

public class ExchangeRate {
    private String baseCode;
    private ExchangeRates rates;
    private long timeLastUpdateUnix;
    private long timeNextUpdateUnix;

    public ExchangeRate() {
        this.rates = new ExchangeRates();
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public ExchangeRates getRates() {
        return rates;
    }

    public void setRates(ExchangeRates rates) {
        this.rates = rates;
    }

    public long getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    public void setTimeLastUpdateUnix(long timeLastUpdateUnix) {
        this.timeLastUpdateUnix