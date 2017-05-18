package sk.desatnik.tracker;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;

public class NetAmountDisplayService {

    protected static DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#######0.##", symbol);
    }

    protected final PaymentTracker<? extends Object> paymentTracker;

    protected final CurrencyExchangeRates<? extends Object> currencyExchangeRates;

    public NetAmountDisplayService(final PaymentTracker<? extends Object> paymentTracker, final CurrencyExchangeRates<? extends Object> currencyExchangeRates) {
        this.paymentTracker = paymentTracker;
        this.currencyExchangeRates = currencyExchangeRates;
    }

    /**
     * Print to the output console currencies and their net amounts.
     * In case currency has exchange rate, USD corresponding value is also printed
     * Example for printing:<br/>
     * EUR 25.5<br/>
     * EUR 11 (USD 12.1)
     */
    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void displayNetAmounts() {
        Map<String, Double> netAmounts = paymentTracker.getNetAmounts();
        netAmounts.forEach((currency, value) -> {
            if (currencyExchangeRates != null && currencyExchangeRates.hasCurrencyExchangeRate(currency)) {
                printNetAmount(currency, value, currencyExchangeRates.convert(currency, value));
            } else {
                printNetAmount(currency, value);
            }
        });
    }

    protected void printNetAmount(final String currency, final double amount) {
        System.out.println(currency + " " + decimalFormat.format(amount));
    }

    protected void printNetAmount(final String currency, final double amount, final double USDValue) {
        System.out.println(currency + " " + decimalFormat.format(amount) + " (USD " + decimalFormat.format(USDValue) + ")");
    }

}
