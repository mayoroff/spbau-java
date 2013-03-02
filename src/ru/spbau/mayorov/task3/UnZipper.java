package ru.spbau.mayorov.task3;

import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * Unzips everything from file specified.
 */
public abstract class UnZipper {

    /** Stream using for input.
     * DataInputStream provides some useful methods.
     */
    private static DataInputStream in = null;

    /** Extracts everything from provided zipFileName.
     *
     * @param zipFileName specified compressed file.
     * @throws FileNotFoundException thrown is zipFileName is not found.
     */
    public static void extract(final String zipFileName)
            throws FileNotFoundException {


        ZipInputStream zip = new ZipInputStream(
                new FileInputStream(zipFileName));


        try {
            zip.getNextEntry();
            in = new DataInputStream(zip);

            System.out.println("Unpacking from " + zipFileName + " ...");
            while (in.available() != 0) {

                String name = in.readUTF();
                System.out.println(name);
                long size = in.readLong();
                File f = new File(name);

                f.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(name);
                StreamUtils.copy(in, out, size);
                out.close();
            }

        } catch (EOFException e) {
            System.err.println("EOF");
            //it's ok. File ended.
            //Sorry for this, but read/mark for ZipInputStream unsupported.
        } catch (IOException e) {
            System.err.println("Problem while unzipping.");
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                System.err.println("Problem while closing input file.");
            } finally {
                in = null;
            }

        }
    }



}
