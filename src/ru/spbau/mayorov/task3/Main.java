package ru.spbau.mayorov.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Arseny Mayorov.
 *         Date: 02.03.13
 */
public abstract class Main {

    /** Minimum arguments number for all operations: compress and decompress. */
    private static final int MIN_ARGUMENTS_NUMBER_GLOBAL = 2;
    /** Minimum arguments number for compress operation. */
    public static final int MIN_ARGUMENTS_NUMBER_COMPRESS = 3;


    /** Entry point.
     *
     * @param args - arguments passed from command line.
     */
    public static void main(final String[] args) {

        if (args.length < MIN_ARGUMENTS_NUMBER_GLOBAL) {
            showUsage();
        }

        if (args[0].contentEquals("compress")) {
            compress(args);
        } else if (args[0].contentEquals("decompress")) {
            decompress(args[1]);
        } else {
            showUsage();
        }
    }

    /** UnZipper utilization method.
     *
     * @param arg path to input zip file.
     */
    private static void decompress(final String arg) {
        try {
            UnZipper.extract(arg);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found");
        }
    }

    /** Zipper utilization method.
     *
     * @param args all command line args.
     */
    private static void compress(final String[] args) {
        if (args.length < MIN_ARGUMENTS_NUMBER_COMPRESS) {
            showUsage();
        }

        Zipper zipper = null;
        try {
            zipper = new Zipper(args[1]);
            for (int i = 2; i < args.length; i++) {
                DirectoryWalker.listFilesFor(new File(args[i]), zipper);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Problem with output file.");
        } finally {
            if (zipper != null) {
                try {
                    zipper.close();
                } catch (IOException e) {
                    System.err.println("Zipper closed with problems");
                }
            }
        }
    }

    /** Shows short instruction on program usage. */
    private static void showUsage() {
        System.out.println("Usage: compress|decompress outputFile "
                + "inputFile1|inputDir1 inputFile2 ...");
    }

}
