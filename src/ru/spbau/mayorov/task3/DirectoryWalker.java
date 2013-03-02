package ru.spbau.mayorov.task3;

import ru.spbau.mayorov.task2.PatternFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

/**
 * Class for recursive walk on specified directory.
 */
public abstract class DirectoryWalker {

    /** Regexp filter to exclude parent dirs. */
    private static PatternFilter filter = new PatternFilter("\\..*");

    /** Starts recursive walk on specified dir.
     * Listener is called only on files, not dirs.
     * If f is file then listener will be notified only once.
     *
     * @param f specified file.
     * @param listener listener, which onFile() method called
     *                 for every found file.
     * @throws FileNotFoundException thrown if file f is not found.
     */
    public static void listFilesFor(final File f,
                                    final DirectoryWalkerListener listener)
                                    throws FileNotFoundException {
        if (!f.exists()) {
            throw new FileNotFoundException(f.getAbsolutePath() + " not found.");
        }

        try {
            if (f.canRead()) {

                File[] children = f.listFiles(filter);

                //We are not interested in listing dirs.
                if (!f.isDirectory()) {
                    listener.onFile(f);
                }

                //Skip empty dirs.
                if (children == null) {
                    return;
                }

                if (f.isDirectory()) {
                    for (File child: children) {
                        listFilesFor(child, listener);
                    }
                }
            }
        } catch (AccessControlException e) {
            System.err.println("Access denied to " + f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while reading " + f.getAbsolutePath());
        }
    }

}
