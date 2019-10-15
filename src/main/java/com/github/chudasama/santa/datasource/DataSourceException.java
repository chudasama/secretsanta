package com.github.chudasama.santa.datasource;

/**
 *  the <code>DataSourceException</code> represent the exception  associated with data source.
 */
public class DataSourceException extends Exception {
    public DataSourceException() {
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }

    public DataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
