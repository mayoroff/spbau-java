package ru.spbau.mayorov.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads message from file with specific format.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public class FileMessageReader {

    private BufferedReader bufferedReader;

    /** Constructs FileMessageReader for give fileName.
     *
     * @param fileName file to read from.
     * @throws FileNotFoundException thrown when file not found.
     */
    public FileMessageReader(String fileName)
                       throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    /** Reads message from file.
     *
     * @return message object representing read message.
     * @throws IOException throwed in case of IO error occured.
     * @throws IllegalMessageFormatException throwed in case of bad file format.
     */
    Message readMessage() throws IOException, IllegalMessageFormatException {
        Message msg;
        String numOfLines = bufferedReader.readLine();
        if (numOfLines == null) {
            return null;
        }

        try {
            int lines = Integer.parseInt(numOfLines);
            ArrayList<String> content = new ArrayList<String>();
            for (int i = 0; i < lines; i++) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    throw new IllegalMessageFormatException("Failed to read line");
                }
                content.add(line);
            }
            msg = new Message(content);

        } catch (NumberFormatException e) {
            throw new IllegalMessageFormatException("Failed to get number of lines");
        }

        return msg;
    }

    /** Closes reader. */
    public void close() throws IOException {
        bufferedReader.close();
    }
}
