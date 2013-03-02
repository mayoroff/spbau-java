package ru.spbau.mayorov.task3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contains some useful methods for streams manipulation.
 */
public abstract class StreamUtils {

    /** Reading buffer size. */
    private static final int BLOCK_SIZE = 1024;

    /** Copies everything from in to out.
     *
     * @param in input stream.
     * @param out output stream.
     * @throws IOException something gone wrong while reading/writing.
     */
    public static void copy(final InputStream in, final OutputStream out)
            throws IOException {
        byte[] buffer = new byte[BLOCK_SIZE];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }

    /** Copies specified amount of bytes from in to out.
     *
     * @param in input stream.
     * @param out output stream.
     * @param size number of bytes to copy.
     * @throws IOException something gone wrong while reading/writing.
     */
    public static void copy(final InputStream in, final OutputStream out,
                            final long size) throws IOException {
        byte[] buffer = new byte[BLOCK_SIZE];
        int len;
        long remained = size;
        while (((len = in.read(buffer,
                        0,
                        (int) Math.min(remained, BLOCK_SIZE))) != -1)
                && (remained > 0)) {
            out.write(buffer, 0, len);
            remained -= len;
        }
    }


}
