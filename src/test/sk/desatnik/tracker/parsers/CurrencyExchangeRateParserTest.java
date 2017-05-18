package sk.desatnik.tracker.parsers;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import sk.desatnik.tracker.objects.CurrencyExchangeRate;

public class CurrencyExchangeRateParserTest {

    @Test
    public void testParse() {
        CurrencyExchangeRateParser currencyExchangeRateParser = new CurrencyExchangeRateParser();

        try {
            CurrencyExchangeRate currencyExchangeRate = currencyExchangeRateParser.parse("HKM 100");
            assertEquals(new CurrencyExchangeRate("HKM", 100), currencyExchangeRate);
        } catch (ParseException e) {
            fail("ParseException " + e.getMessage());
        }

        try {
            CurrencyExchangeRate currencyExchangeRate = currencyExchangeRateParser.parse("EUR 1.2");
            assertEquals(new CurrencyExchangeRate("EUR", 1.2), currencyExchangeRate);
        } catch (ParseException e) {
            fail("ParseException " + e.getMessage());
        }
    }

    @Test(expected = ParseException.class)
    public void testParseException() throws ParseException {
        CurrencyExchangeRateParser currencyExchangeRateParser = new CurrencyExchangeRateParser();

        currencyExchangeRateParser.parse("USDx 1");

        currencyExchangeRateParser.parse("EUR -1.2");

        currencyExchangeRateParser.parse("EuR 1.2");

        currencyExchangeRateParser.parse(" EUR 1.2");

        currencyExchangeRateParser.parse("EUR 1.0 ");
    }

}
