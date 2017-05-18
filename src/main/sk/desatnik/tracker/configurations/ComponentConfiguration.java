package sk.desatnik.tracker.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.desatnik.tracker.CurrencyExchangeRates;
import sk.desatnik.tracker.DefaultCurrencyExchangeRates;
import sk.desatnik.tracker.DefaultPaymentTracker;
import sk.desatnik.tracker.NetAmountDisplayService;
import sk.desatnik.tracker.PaymentTrackerApplication;
import sk.desatnik.tracker.PaymentTracker;
import sk.desatnik.tracker.inputprocessors.CurrencyExchangeRateInputProcessor;
import sk.desatnik.tracker.inputprocessors.InputProcessor;
import sk.desatnik.tracker.inputprocessors.PaymentInputProcessor;
import sk.desatnik.tracker.objects.CurrencyExchangeRate;
import sk.desatnik.tracker.objects.Payment;
import sk.desatnik.tracker.parsers.CurrencyExchangeRateParser;
import sk.desatnik.tracker.parsers.Parser;
import sk.desatnik.tracker.parsers.PaymentParser;
import sk.desatnik.tracker.readers.ConsoleReader;
import sk.desatnik.tracker.readers.FileReader;

@Configuration
public class ComponentConfiguration {

    @Bean
    public PaymentTracker<Payment> defaultPaymentTracker() {
        return new DefaultPaymentTracker();
    }

    @Bean
    public CurrencyExchangeRates<CurrencyExchangeRate> defaultCurrencyExchangeRates() {
        return new DefaultCurrencyExchangeRates();
    }

    @Bean
    public Parser<Payment> paymentParser() {
        return new PaymentParser();
    }

    @Bean
    public Parser<CurrencyExchangeRate> currencyExchangeRateParser() {
        return new CurrencyExchangeRateParser();
    }

    @Bean
    @Qualifier("paymentInputProcessor")
    @Autowired
    public InputProcessor paymentTransferInputProcessor(final PaymentTracker<Payment> paymentTracker, final Parser<Payment> paymentParser) {
        return new PaymentInputProcessor(paymentTracker, paymentParser);
    }

    @Bean
    @Qualifier("currencyExchangeRateInputProcessor")
    @Autowired
    public InputProcessor currencyExchangeRateInputProcessor(final CurrencyExchangeRates<CurrencyExchangeRate> currencyExchangeRates, final Parser<CurrencyExchangeRate> currencyExchangeRateParser) {
        return new CurrencyExchangeRateInputProcessor(currencyExchangeRates, currencyExchangeRateParser);
    }

    @Bean
    @Autowired
    public NetAmountDisplayService netAmountDisplayService(PaymentTracker<Payment> paymentTracker, CurrencyExchangeRates<CurrencyExchangeRate> currencyExchangeRates) {
        return new NetAmountDisplayService(paymentTracker, currencyExchangeRates);
    }

    @Bean
    @Autowired
    public ConsoleReader consoleReader(@Qualifier("paymentTransferInputProcessor") InputProcessor inputProcessor) {
        return new ConsoleReader(inputProcessor);
    }

    @Bean
    @Qualifier("paymentsFileReader")
    @Autowired
    public FileReader paymentsFileReader(@Qualifier("paymentInputProcessor") InputProcessor inputProcessor) {
        return new FileReader(inputProcessor);
    }

    @Bean
    @Qualifier("currencyExchangeRatesFileReader")
    @Autowired
    public FileReader currencyExchangeRatesFileReader(@Qualifier("currencyExchangeRateInputProcessor") InputProcessor inputProcessor) {
        return new FileReader(inputProcessor);
    }

    @Bean
    @Autowired
    public PaymentTrackerApplication application(final ConsoleReader consoleReader, @Qualifier("paymentsFileReader") final FileReader paymentsFileReader,
            @Qualifier("currencyExchangeRatesFileReader") final FileReader currencyExchangeRatesFileReader) {
        return new PaymentTrackerApplication(consoleReader, paymentsFileReader, currencyExchangeRatesFileReader);
    }
}
