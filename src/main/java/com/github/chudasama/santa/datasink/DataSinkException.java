package com.github.chudasama.santa.datasink;

/**
 * The DataSinkException exception represent the error associated with data sink operation.
 */
public class DataSinkException extends Exception {
    public DataSinkException() {
    }

    public DataSinkException(String message) {
        super(message);
    }

    public DataSinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSinkException(Throwable cause) {
        super(cause);
    }

    public DataSinkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
