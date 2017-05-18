package sk.desatnik.tracker.parsers;

import java.text.ParseException;

public interface Parser<T> {

    /**
     * Parse text
     * @param text
     * @return
     * @throws ParseException text has wrong format and cannot be parsed
     */
    public T parse(String text) throws ParseException;

}
