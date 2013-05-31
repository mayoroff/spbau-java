package ru.spbau.mayorov.task1;

import java.io.IOException;

/**
 * Merges two messages into one.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public class CompressingMessageWriter implements MessageWriter {

    /** External message writer. */
    private MessageWriter writer = null;
    /** Message saved to append another. */
    private Message firstMessage = null;

    /** Constructs CompressingMessageWriter using external MessageWriter.
     *
     * @param wr - external MessageWriter.
     */
    CompressingMessageWriter(MessageWriter wr) {
        this.writer = wr;
    }

    @Override
    public void messageWrite(Message msg) throws IOException {
        if (firstMessage == null) {
            firstMessage = msg;
        } else {
            firstMessage.append(msg);
            writer.messageWrite(firstMessage);
            firstMessage = null;
        }
    }

    /** Writes saved message to output stream, if exists.
     *  Should be called after last message was passed to messageWrite() method.
     *
     * @throws IOException thrown when IO error occurs.
     */
    public void flush() throws IOException {
        if (firstMessage != null) {
            writer.messageWrite(firstMessage);
        }
        firstMessage = null;
    }

    /** Closes writer. */
    public void close() throws IOException {
        writer.close();
    }
}
