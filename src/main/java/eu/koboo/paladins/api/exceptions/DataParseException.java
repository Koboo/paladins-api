package eu.koboo.paladins.api.exceptions;

public class DataParseException extends RuntimeException {

    public DataParseException(Class<?> dataClass, Throwable t) {
        super("Couldn't parse json to data class \"" + dataClass.getName() + "\".", t);
    }
}
