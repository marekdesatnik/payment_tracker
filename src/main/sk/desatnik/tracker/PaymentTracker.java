package sk.desatnik.tracker;

import java.util.Map;

public interface PaymentTracker<T> {

    /**
     * Add payment
     * @param payment object representing payment
     */
    public void addPayment(T payment);

    /**
     * @return map of net amount for currencies. Return map does not contain entry in case net amount for currency equals 0
     */
    public Map<String, Double> getNetAmounts();

}
