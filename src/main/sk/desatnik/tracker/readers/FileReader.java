package sk.desatnik.tracker.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import sk.desatnik.tracker.inputprocessors.InputProcessor;

public class FileReader implements Reader {

    protected final InputProcessor inputProcessor;

    protected String fileName;

    public FileReader(final InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    /**
     * Set file name for reading from it
     * @param fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read file content and process it
     */
    @Override
    public void read() {
        if (fileName != null) {
            BufferedReader inputFile = null;
            try {
                inputFile = new BufferedReader(new java.io.FileReader(fileName));
                inputFile.lines().forEach(input -> inputProcessor.processInput(input));
            } catch (FileNotFoundException e) {
                System.err.println("File does not exist: '" + fileName + "'");
            } finally {
                if (inputFile != null) {
                    try {
                        inputFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
