package sk.desatnik.tracker.inputprocessors;

import java.text.ParseException;

import sk.desatnik.tracker.CurrencyExchangeRates;
import sk.desatnik.tracker.objects.CurrencyExchangeRate;
import sk.desatnik.tracker.parsers.Parser;

public class CurrencyExchangeRateInputProcessor implements InputProcessor {

    protected final CurrencyExchangeRates<CurrencyExchangeRate> currencyExchangeRates;

    protected final Parser<CurrencyExchangeRate> currencyExchangeRateFormat;

    public CurrencyExchangeRateInputProcessor(final CurrencyExchangeRates<CurrencyExchangeRate> currencyExchangeRates, final Parser<CurrencyExchangeRate> currencyExchangeRateFormat) {
        this.currencyExchangeRates = currencyExchangeRates;
        this.currencyExchangeRateFormat = currencyExchangeRateFormat;
    }

    /**
     * Process line of text input for currency exchange rate
     * @param text
     */
    @Override
    public void processInput(final String text) {
        try {
            CurrencyExchangeRate currencyExchangeRate = currencyExchangeRateFormat.parse(text);
            currencyExchangeRates.addCurrencyExchangeRate(currencyExchangeRate.getCurrency(), currencyExchangeRate);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

    }

}
