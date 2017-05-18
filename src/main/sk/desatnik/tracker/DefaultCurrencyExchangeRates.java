package sk.desatnik.tracker;

import java.util.HashMap;
import java.util.Map;

import sk.desatnik.tracker.objects.CurrencyExchangeRate;

public class DefaultCurrencyExchangeRates implements CurrencyExchangeRates<CurrencyExchangeRate> {

    protected final Map<String, CurrencyExchangeRate> currencyExchangeRates = new HashMap<>();

    @Override
    public void addCurrencyExchangeRate(final String currency, final CurrencyExchangeRate currencyExchangeRate) {
        currencyExchangeRates.put(currency, currencyExchangeRate);
    }

    @Override
    public CurrencyExchangeRate getCurrencyExchangeRate(final String currency) {
        return currencyExchangeRates.get(currency);
    }

    @Override
    public boolean hasCurrencyExchangeRate(final String currency) {
        return currencyExchangeRates.get(currency) != null;
    }

    @Override
    public double convert(final String currency, final double value) {
        if (hasCurrencyExchangeRate(currency)) {
            return getCurrencyExchangeRate(currency).getRate() * value;
        } else {
            throw new IllegalArgumentException("No CurrencyExchangeRate for '" + currency + "'");
        }
    }


}
