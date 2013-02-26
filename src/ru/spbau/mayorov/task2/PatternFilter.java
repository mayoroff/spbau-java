package ru.spbau.mayorov.task2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Arseny Mayorov.
 *         Date: 24.02.13
 */
public class PatternFilter implements FilenameFilter {

    /** Object that stores information about regexp. */
    private Pattern pattern = null;

    /** Constructs PatternFilter for given regexp.
     *
     * @param regex regular expression in string representation.
     */
    PatternFilter(final String regex) {
         pattern = Pattern.compile(regex);
    }

    @Override
    public final boolean accept(final File dir, final String name) {
        Matcher m = pattern.matcher(name);
        return !m.matches();
    }
}
