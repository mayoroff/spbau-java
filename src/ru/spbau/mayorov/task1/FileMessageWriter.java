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
public class FileMessageWriter implements MessageWriter {

    /** Constructs FileMessageWriter using give fileName.
     *
     * @param fileName - file for output.
     * @throws IOException thrown in case of IO error.
     */

    private BufferedWriter bufferedWriter;

    public FileMessageWriter(final String fileName) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void messageWrite(final Message msg) throws IOException {
        List<String> content = msg.getLines();
        int linesNum = content.size();
        bufferedWriter.write(String.valueOf(linesNum));
        bufferedWriter.newLine();
        for (String line: content) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    /** Closes writer. */
    public void close() throws IOException {
        bufferedWriter.close();
    }
}
