package ru.spbau.mayorov.controlwork;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Arseny Mayorov.
 *         Date: 27.03.13
 */
public class Tournament <T extends Game> implements Iterable<T> {

    private ArrayList<Player> players = new ArrayList<Player>();

    public Tournament(String playersPath, Class<T> gameClass, Object[] gameArgs) throws MalformedURLException  {

        Class[] argsClass = new Class[gameArgs.length];
        for (int i = 0; i < gameArgs.length; i++) {
            argsClass[i] = gameArgs[i].getClass();
        }

        File classDir = new File(playersPath);

        URL[] url = { classDir.toURI().toURL() };
        URLClassLoader urlLoader = new URLClassLoader(url);

        String filename;
        for (File file : classDir.listFiles()) {
            filename = file.getName();

            if (!filename.endsWith(".class") || filename.equals(".") || filename.equals("..") || filename.startsWith("."))
                continue;

            filename = filename.substring(0, filename.lastIndexOf('.'));

            Player instance = null;
            try {
                instance = (Player)urlLoader
                        .loadClass("ru.spbau.mayorov.controlwork." + filename)
                        .getConstructor(argsClass)
                        .newInstance(gameArgs);
                System.out.println("loaded -> " + instance.toString());

                if (instance.getGameType() == gameClass) {
                    System.out.println("qualified -> " + instance.toString());
                    players.add(instance);
                }

            } catch (InstantiationException e) {
                System.err.println("Failed to load " + filename);
            } catch (IllegalAccessException e) {
                System.err.println("Failed to load " + filename);
            } catch (InvocationTargetException e) {
                System.err.println("Failed to load " + filename);
            } catch (NoSuchMethodException e) {
                System.err.println("Failed to load " + filename);
            } catch (ClassNotFoundException e) {
                System.err.println("Failed to load " + filename);
            }





        }
    }

    @Override
    public Iterator<T> iterator() {
        assert (false);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
