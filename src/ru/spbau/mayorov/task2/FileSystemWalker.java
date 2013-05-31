package ru.spbau.mayorov.task2;

import java.io.File;
import java.security.AccessControlException;
import java.util.*;

/**
 * @author Arseny Mayorov.
 *         Date: 24.02.13
 */
public class FileSystemWalker {

    /** Path for scanning root. */
    private String rootPath = null;
    /** Filename filter (must implement FilenameFilter interface). */
    private PatternFilter filter = null;
    /** Array used for storing result tree. */
    private ArrayList<String> output = new ArrayList<String>();

    /** Constructs FileSystemWalker for given root path and filename filter.
     *
     * @param path path to start from.
     * @param flt filename filter object.
     */
    public FileSystemWalker(final String path, final PatternFilter flt) {
        this.rootPath = path;
        this.filter = flt;
    }

    /** Returns strings representing formatted tree of filesystem structure.
     * May take a lot of time for large hierarchy.
     *
     * @return list of strings representing tree of filesystem structure.
     */
    public final List<String> listFiles() {
        File f = new File(rootPath);
        processFile(f, new HashSet<Integer>());
        ArrayList<String> result = output;
        output = null;
        return result;
    }

    /** Internal method used to scan directories recursively.
     *
     * @param f file descriptor.
     * @param levels nesting level indicator.
     */
    private void processFile(final File f, Set<Integer> levels) {

        String name = new String();

        for (int i = 0; levels.size() !=0 && i < Collections.max(levels); i++) {

            if (levels.contains(i)) {
                name += "|";
            }
            else {
                name += " ";
            }

        }

        try {
            name += levels.size() == 0 ? "" : "|_";
            if (!f.canRead()) {
                name += f.getName() + " (access denied)";
            } else {
                name += f.getName();
                output.add(name);
                File[] children = f.listFiles(filter);
                if (children == null) {
                    return;
                }
                for (File child: children) {
                    if (f.isDirectory()) {
                        Set l = new HashSet(levels);
                        Integer lvl = levels.size() == 0 ? 0 : Collections.max(levels) + 2;
                        l.add(lvl + f.getName().length());
                        processFile(child, l);
                    }
                }
            }
        } catch (AccessControlException e) {
            name += f.getName() + " (access denied)";
            output.add(name);
        }
    }
}
