package sk.desatnik.tracker;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sk.desatnik.tracker.objects.Payment;

public class DefaultPaymentTrackerTest {

    private PaymentTracker<Payment> paymentTracker;

    @Before
    public void beforeTest() {
        paymentTracker = new DefaultPaymentTracker();

        paymentTracker.addPayment(new Payment("USD", 20));
        paymentTracker.addPayment(new Payment("EUR", 40));
        paymentTracker.addPayment(new Payment("HKD", 80));
        paymentTracker.addPayment(new Payment("USD", -10));
        paymentTracker.addPayment(new Payment("HKD", -50));
        paymentTracker.addPayment(new Payment("EUR", 10));
        paymentTracker.addPayment(new Payment("HKD", -30));
    }

    @Test
    public void testGetNetAmounts() {
        Map<String, Double> netAmounts = paymentTracker.getNetAmounts();

        assertEquals(Double.valueOf(10), netAmounts.get("USD"));

        assertEquals(Double.valueOf(50), netAmounts.get("EUR"));
    }

    @Test
    public void testGetNetAmountsZero() {
        Map<String, Double> netAmounts = paymentTracker.getNetAmounts();

        assertNull(netAmounts.get("HKD"));
    }


}
