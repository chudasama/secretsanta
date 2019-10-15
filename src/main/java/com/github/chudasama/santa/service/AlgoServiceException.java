package com.github.chudasama.santa.service;

/**
 * Algo Exceptions
 */
public class AlgoServiceException extends Exception {
    public AlgoServiceException() {
    }

    public AlgoServiceException(String s) {
        super(s);
    }

    public AlgoServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AlgoServiceException(Throwable throwable) {
        super(throwable);
    }

    public AlgoServiceException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
