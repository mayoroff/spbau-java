package ru.spbau.mayorov.task2;

import java.util.List;

/**
 * @author Arseny Mayorov.
 *         Date: 26.02.13
 */
public abstract class Main {

    /** Entry point.
     *
     * @param args - arguments passed from command line.
     */
    public static void main(final String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: Main path");
            return;
        }

        PatternFilter filter = new PatternFilter("\\..*");
        FileSystemWalker walker = new FileSystemWalker(args[0], filter);

        List<String> tree = walker.listFiles();
        for (String line:tree) {
            System.out.println(line);
        }

    }

}
