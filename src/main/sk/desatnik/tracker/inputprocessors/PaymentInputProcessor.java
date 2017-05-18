package sk.desatnik.tracker.inputprocessors;

import java.text.ParseException;

import sk.desatnik.tracker.PaymentTracker;
import sk.desatnik.tracker.objects.Payment;
import sk.desatnik.tracker.parsers.Parser;

public class PaymentInputProcessor implements InputProcessor {

    protected final PaymentTracker<Payment> paymentTracker;

    protected final Parser<Payment> paymentParser;

    public PaymentInputProcessor(final PaymentTracker<Payment> paymentTracker, final Parser<Payment> paymentParser) {
        this.paymentTracker = paymentTracker;
        this.paymentParser = paymentParser;
    }

    /**
     * Process line of text input for payment
     * @param text
     */
    @Override
    public void processInput(final String text) {
        try {
            Payment payment = paymentParser.parse(text);
            paymentTracker.addPayment(payment);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }

}
