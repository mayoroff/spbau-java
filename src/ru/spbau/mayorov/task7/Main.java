package ru.spbau.mayorov.task7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Main class.
 * @author Arseny Mayorov.
 */
public class Main {


    /** Entry point.
     * @param args command line arguments
     * */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println(".properties file not specified");
            return;
        }

        try (FileInputStream in = new FileInputStream(args[0])) {

            Properties properties = new Properties();
            properties.load(in);

            Student st = new Student();
            ReflectionDeSerializer.deserialize(properties, st);

            st.setAvgGrade(Math.min(st.getAvgGrade() + 1, 5.0));

            ReflectionSerializer.serialize(properties, st);

            try (FileOutputStream out = new FileOutputStream(args[0])) {
                properties.store(out, "");
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("Problem reading file.");
        }


    }

}
