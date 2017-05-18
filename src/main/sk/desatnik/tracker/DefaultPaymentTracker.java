package sk.desatnik.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import sk.desatnik.tracker.objects.Payment;

public class DefaultPaymentTracker implements PaymentTracker<Payment> {

    protected final List<Payment> payments = new ArrayList<>();

    @Override
    public synchronized void addPayment(final Payment payment) {
        payments.add(payment);
    }

    @Override
    public synchronized Map<String, Double> getNetAmounts() {
        return payments.stream()
                //group by currency and sum values
                .collect(Collectors.groupingBy(Payment::getCurrency, Collectors.summingDouble(Payment::getAmount)))

                //filter by value (value != 0)
                .entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
