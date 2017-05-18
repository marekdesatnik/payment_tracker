package sk.desatnik.tracker.objects;

/**
 *Object representing currency exchange rate
 */
public class CurrencyExchangeRate {

    private final String currency;
    private final double rate;

    public CurrencyExchangeRate(final String currency, final double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    /**
     * @return code of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return currency exchange rate
     */
    public double getRate() {
        return rate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        long temp;
        temp = Double.doubleToLongBits(rate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CurrencyExchangeRate other = (CurrencyExchangeRate) obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRate [currency=" + currency + ", rate=" + rate + "]";
    }


}
