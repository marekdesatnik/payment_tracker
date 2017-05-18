package sk.desatnik.tracker.readers;

import java.io.Console;

import sk.desatnik.tracker.inputprocessors.InputProcessor;

public class ConsoleReader implements Reader {

    protected final InputProcessor inputProcessor;

    public ConsoleReader(final InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    /**
     * Read input from console and process it.
     * Reading from console is stopped when line of input equals 'quit'
     */
    @Override
    public void read() {
        String input = null;
        Console console = System.console();
        if (console != null) {
            while (!"quit".equals(input = console.readLine())) {
                inputProcessor.processInput(input);
            }
        } else {
            throw new NullPointerException("Console is required for reading input!");
        }
    }

}
