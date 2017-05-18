package sk.desatnik.tracker.objects;

/**
 * Object representing Payment
 */
public class Payment {

    private final String currency;
    private final double amount;

    public Payment(final String currency, final double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    /**
     * @return code of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return transfered amount
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
        Payment other = (Payment) obj;
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Payment [currency=" + currency + ", amount=" + amount + "]";
    }


}
