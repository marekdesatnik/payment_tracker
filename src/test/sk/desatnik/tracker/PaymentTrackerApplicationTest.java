package sk.desatnik.tracker;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.desatnik.tracker.configurations.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class PaymentTrackerApplicationTest {

    @Autowired
    private PaymentTrackerApplication application;

    @Autowired
    private NetAmountDisplayService netAmountDisplayService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
    }

    @Test
    public void applicationTest() {
        application.readFiles("-i", "data/test/payments.txt", "-r", "data/test/rates.txt");

        netAmountDisplayService.displayNetAmounts();

        try {
            String expectedResult = loadFile("data/test/expected.txt");

            assertEquals(expectedResult, outContent.toString());

            assertEquals("", errContent.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String loadFile(final String fileName) throws Exception {
        String result = null;
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new java.io.FileReader(fileName));
            result = inputFile.lines().collect(Collectors.joining("\n", "", "\n"));
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
