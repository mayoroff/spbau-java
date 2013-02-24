package ru.spbau.mayorov.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents text message.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public final class Message {

    /** Contains content lines. */
    private List<String> contents = new ArrayList<String>();


    /** Constructs message from given strings.
     *
     * @param lines  List of message lines.
     */
    Message(final List<String> lines) {
        this.contents.addAll(lines);
    }

    /** Append content of given message to current.
     *
     * @param msg Given message.
     */
    void append(final Message msg) {
        this.contents.addAll(msg.contents);
    }

    /** Returns unmodifiable list of message lines.
     *
     * @return unmodifiable list of message lines.
     */
    List<String> getLines() {
        return Collections.unmodifiableList(contents);
    }

}
