package sk.desatnik.tracker;

public interface CurrencyExchangeRates<T> {

    /**
     * Register currency exchange rate
     * @param currency code of currency
     * @param currencyExchangeRate object representing currency exchange rate
     */
    public void addCurrencyExchangeRate(String currency, T currencyExchangeRate);

    /**
     * @param currency code of currency
     * @return object representing currency exchange rate
     */
    public T getCurrencyExchangeRate(String currency);

    /**
     * @param currency code of currency
     * @return true if currency is registered
     */
    public boolean hasCurrencyExchangeRate(String currency);

    /**
     * Convert value with currency exchange rate
     * @param currency code of currency
     * @param value converting value
     * @return converted value
     */
    public double convert(String currency, double value);

}
