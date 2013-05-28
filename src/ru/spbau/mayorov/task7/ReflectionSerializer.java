package ru.spbau.mayorov.task7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Serializes given object into properties.
 * @author Arseny Mayorov.
 */
public class ReflectionSerializer {

    /** Gets property's name for given method's name.
     * @param mName method's name
     * @return property's name
     */
    private static String methodNameToPropertyName(String mName) {
        return mName.substring(3, 4).toLowerCase() + mName.substring(4);
    }

    /** Serializes given object into given properties.
     *
     * @param properties properties object
     * @param obj instance of object to serialize
     * @param <T> class of serialized object
     */
    public static <T> void serialize(Properties properties, T obj) {

        for (Method m: obj.getClass().getMethods()) {
            if (!m.getName().substring(0, 3).equals("get"))
                continue;

            try {
                Object result = m.invoke(obj);
                properties.setProperty(methodNameToPropertyName(m.getName()), result.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }

        }

    }

}
