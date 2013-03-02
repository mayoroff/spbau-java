package ru.spbau.mayorov.task3;

import java.io.File;
import java.io.IOException;

/**
 * @author Arseny Mayorov.
 *         Date: 02.03.13
 */
public interface DirectoryWalkerListener {

    /** Method called when DirectoryWalker founds new file.
     *
     * @param f file that was found.
     * @throws IOException DirectoryWalkerListener could
     */
    void onFile(File f) throws IOException;

}
