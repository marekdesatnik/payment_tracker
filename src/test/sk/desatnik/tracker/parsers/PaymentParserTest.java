package sk.desatnik.tracker.parsers;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import sk.desatnik.tracker.objects.Payment;

public class PaymentParserTest {

    @Test
    public void testParse() {
        PaymentParser paymentParser = new PaymentParser();
        try {
            Payment payment = paymentParser.parse("USD 100");
            assertEquals(new Payment("USD", 100), payment);
        } catch (ParseException e) {
            fail("ParseException " + e.getMessage());
        }

        try {
            Payment paymentTransfer = paymentParser.parse("EUR -100.2");
            assertEquals(new Payment("EUR", -100.2), paymentTransfer);
        } catch (ParseException e) {
            fail("ParseException " + e.getMessage());
        }
    }

    @Test(expected = ParseException.class)
    public void testParseException() throws ParseException {
        PaymentParser paymentParser = new PaymentParser();

        paymentParser.parse("USDx 100");

        paymentParser.parse("EUR -100.2");

        paymentParser.parse("EuR -100.2");

        paymentParser.parse(" EUR -100.2");

        paymentParser.parse("EUR -100 ");
    }

}
