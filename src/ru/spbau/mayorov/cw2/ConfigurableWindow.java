package ru.spbau.mayorov.cw2;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @author Arseny Mayorov.
 *         Date: 15.05.13
 */
public class ConfigurableWindow {

    private static JFrame myWindow;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: ConfigurableWindow <config-file>");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"))) {

            String windowWH = reader.readLine();
            Scanner s = new Scanner(windowWH);
            int windowW = s.nextInt();
            int windowH = s.nextInt();

            myWindow = new JFrame("ConfigurableWindow");
            myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myWindow.setSize(windowW, windowH);
            myWindow.setVisible(true);


            s = new Scanner(reader.readLine());
            int numOfComponents = s.nextInt();

            for(int i = 0; i < numOfComponents; i++) {

                parseComponent(reader.readLine());

            }

        } catch (FileNotFoundException e) {
            System.err.println("Problem not found");
        } catch (IOException e) {
            System.err.println("Problem accessing file");
        }


    }

    private static void parseComponent(String s)  {

        Scanner scanner = new Scanner(s);

        String className = scanner.next();
        String componentName = scanner.findInLine("\".*?\"");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int w = scanner.nextInt();
        int h = scanner.nextInt();


        Class<?> currentClass = null;
        try {
            currentClass = Class.forName("javax.swing." + className);
            Constructor<?> ctor = currentClass.getConstructor(String.class);
            JComponent obj = (JComponent)ctor.newInstance(componentName);
            obj.setBounds(x, y, w, h);
            obj.setName(componentName);


            if (scanner.hasNext()) {
                String staticClassName = scanner.next();
                String staticClassMethod = scanner.next();

                Method method = Class.forName(staticClassName).getMethod(staticClassMethod);
                method.invoke(null);
            }

            myWindow.add(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }


}
