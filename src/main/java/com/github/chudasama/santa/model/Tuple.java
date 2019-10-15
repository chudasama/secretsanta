package com.github.chudasama.santa.model;

/**
 * Generic class to hold pair vlaue
 *
 * @param <T> T type value
 * @param <U> U type value
 */
public class Tuple<T, U> {

    private T first;
    private U second;

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
