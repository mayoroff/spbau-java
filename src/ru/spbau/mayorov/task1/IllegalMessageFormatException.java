package ru.spbau.mayorov.task1;

/**
 * Indicates that file format is bad.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public class IllegalMessageFormatException extends Exception {

    IllegalMessageFormatException(String msg) {
        super(msg);
    }

}
