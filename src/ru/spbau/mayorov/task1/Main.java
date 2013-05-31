package ru.spbau.mayorov.task1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Arseny Mayorov.
 * Date: 24.02.13
 */
public abstract class Main {

    /** Entry point.
     *
     * @param args - arguments passed from command line.
     */
    public static void main(final String[] args) {
        String input;
        String output = null;

        if (args.length > 1) {
            input = args[0];
            output = args[1];
        } else if (args.length == 1) {
            input = args[0];
        } else {
            System.err.println("Usage: Main inputFileName [outputFileName]");
            return;
        }
        FileMessageReader reader = null;
        CompressingMessageWriter writer = null;
        try {
            reader = new FileMessageReader(input);
            Message msg;

            MessageWriter outputStream;
            if (output != null) {
                outputStream = new FileMessageWriter(output);
            } else {
                outputStream = new ConsoleMessageWriter();
            }
            writer = new CompressingMessageWriter(outputStream);
            while ((msg = reader.readMessage()) != null) {
                writer.messageWrite(msg);
            }
            writer.flush();


        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
        } catch (IllegalMessageFormatException e) {
            System.err.println("Bad input file format. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error occured.");
        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Cleanup failed. Sorry.");
            }

        }
    }

}
