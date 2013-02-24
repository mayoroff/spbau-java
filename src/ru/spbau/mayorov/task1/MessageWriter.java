package ru.spbau.mayorov.task1;

import java.io.IOException;

/**
 * Interface for classes used for writing message into different outputs.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public interface MessageWriter {
    /** Writes message in implementation-specific manner.
     *
     * @param msg message to write.
     * @throws IOException thrown in case of IO errors.
     */
    void messageWrite(final Message msg) throws IOException;
}
