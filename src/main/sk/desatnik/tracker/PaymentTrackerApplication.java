package sk.desatnik.tracker;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.desatnik.tracker.configurations.ApplicationConfiguration;
import sk.desatnik.tracker.readers.ConsoleReader;
import sk.desatnik.tracker.readers.FileReader;

public class PaymentTrackerApplication {

    protected final ConsoleReader consoleReader;

    protected final FileReader paymentsFileReader;

    protected final FileReader currencyExchangeRatesFileReader;

    public PaymentTrackerApplication(final ConsoleReader consoleReader, final FileReader paymentsFileReader, final FileReader currencyExchangeRatesFileReader) {
        this.consoleReader = consoleReader;
        this.paymentsFileReader = paymentsFileReader;
        this.currencyExchangeRatesFileReader = currencyExchangeRatesFileReader;
    }

    /**
     * Run application
     * @param args arguments of program
     */
    public void run(String... args) {
        readFiles(args);

        consoleReader.read();
    }

    /**
     * Read input files. Options for defining file names:<br/>
     * <ul>
     * <li>-i file name for payment transfers</li>
     * <li>-r file name for currency exchange rates</li>
     * </ul>
     * @param args arguments of program
     */
    protected void readFiles(String... args) {
        Options options = new Options();
        options.addOption("i", "input", true, "File name for payments");
        options.addOption("r", "rates", true, "File name for currency exchange rates");

        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);

            if (commandLine.hasOption("i")) {
                String fileName = commandLine.getOptionValue("i");

                paymentsFileReader.setFileName(fileName);
                paymentsFileReader.read();
            }

            if (commandLine.hasOption("r")) {
                String fileName = commandLine.getOptionValue("r");

                currencyExchangeRatesFileReader.setFileName(fileName);
                currencyExchangeRatesFileReader.read();
            }
        } catch (ParseException e) {
            System.err.println("Failed parse args: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        PaymentTrackerApplication application = applicationContext.getBean(PaymentTrackerApplication.class);

        application.run(args);

        applicationContext.close();

        System.exit(0);
    }
}
