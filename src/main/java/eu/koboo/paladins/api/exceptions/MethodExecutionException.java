package eu.koboo.paladins.api.exceptions;

public class MethodExecutionException extends RuntimeException {

    public MethodExecutionException(String method, Throwable t) {
        super("Exception while executing method \"" + method + "\".", t);
    }
}
