package ru.spbau.mayorov.task1;

import java.util.List;

/**
 * Writes message on console.
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public class ConsoleMessageWriter implements MessageWriter {
    /** Message number counter. */
    private int currentMsgNumber = 1;

    @Override
    public void messageWrite(final Message msg) {
        List<String> content = msg.getLines();
        System.out.println("\"Message " + currentMsgNumber + "\"");
        int currentLineNumber = 1;
        for (String line: content) {
            System.out.printf("\"%d.%d. \"%s\n",
                    currentMsgNumber,
                    currentLineNumber,
                    line);

            currentLineNumber++;
        }
        currentMsgNumber++;
    }

    /** Closes writer. */
    @Override
    public void close() {
    }
}
