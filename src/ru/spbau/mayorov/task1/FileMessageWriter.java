package ru.spbau.mayorov.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes message to file in specific format.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public final class FileMessageWriter extends BufferedWriter
                                     implements MessageWriter {

    /** Constructs FileMessageWriter using give fileName.
     *
     * @param fileName - file for output.
     * @throws IOException thrown in case of IO error.
     */
    public FileMessageWriter(final String fileName) throws IOException {
        super(new FileWriter(fileName));
    }

    @Override
    public void messageWrite(final Message msg) throws IOException {
        List<String> content = msg.getLines();
        int linesNum = content.size();
        super.write(String.valueOf(linesNum));
        super.newLine();
        for (String line: content) {
            super.write(line);
            super.newLine();
        }
        super.flush();
    }
}
