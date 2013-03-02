package ru.spbau.mayorov.task3;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zips everything provided via onFile().
 */
public class Zipper implements DirectoryWalkerListener {

    /** Stream using for output.
     * DataOutputStream provides some useful methods.
     */
    private DataOutputStream out = null;

    /** Path to our working directory. Used to calculate file's relative path.
     */
    private String workDir = System.getProperty("user.dir");

    /** Creates Zipper object, that zips everything provided via onFile()
     * to file named zipFileName.
     * @param zipFileName zipped output file.
     * @throws IOException problems with creating output file.
     */
    public Zipper(final String zipFileName) throws IOException {
        ZipOutputStream zip = new ZipOutputStream(
                new FileOutputStream(zipFileName));
        zip.putNextEntry(new ZipEntry(""));
        this.out = new DataOutputStream(zip);
    }

    @Override
    public final void onFile(final File f) {

        System.out.println(getRelativePath(f));

        FileInputStream in = null;

        try {
        out.writeUTF(getRelativePath(f));
        out.writeLong(f.length());

        in = new FileInputStream(f);
        StreamUtils.copy(in, out);
        } catch (FileNotFoundException e) {
            System.err.println(f.getAbsolutePath() + " not found.");
        } catch (IOException e) {
            System.err.println("Problem while zipping " + f.getAbsolutePath());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Problem closing " + f.getAbsolutePath());
                }
            }
        }

    }

    /** Closes Zipper and underlying streams.
     *
     * @throws IOException underlying stream have problems with closing.
     */
    public final void close() throws IOException {
        out.close();
    }

    /** Utility method to get relative path for specified file.
     *
     * @param f file specified.
     * @return String with file's relative path.
     */
    private String getRelativePath(final File f) {
        return new File(workDir).toURI().relativize(f.toURI()).getPath();
    }
}
