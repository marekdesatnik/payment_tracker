package sk.desatnik.tracker.parsers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.desatnik.tracker.objects.CurrencyExchangeRate;

public class CurrencyExchangeRateParser implements Parser<CurrencyExchangeRate> {

    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#######0.##", symbol);
    }

    /**
     * Parse text into CurrencyExchangeRate
     * @param text
     * @return CurrencyExchangeRate object representing currency exchange rate
     * @throws text has wrong format and cannot be parsed
     */
    @Override
    public CurrencyExchangeRate parse(final String text) throws ParseException {
        Pattern pattern = Pattern.compile("([A-Z]{3}) ([0-9]\\d*(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(text);
        if (check(text) && matcher.find()) {
            return new CurrencyExchangeRate(matcher.group(1), decimalFormat.parse(matcher.group(2)).doubleValue());
        } else {
            throw new ParseException("Wrong payment transfer format: '" + text + "'", 0);
        }
    }

    private boolean check(final String text) {
        return text.matches("[A-Z]{3} [0-9]\\d*(\\.\\d+)?");
    }

}
