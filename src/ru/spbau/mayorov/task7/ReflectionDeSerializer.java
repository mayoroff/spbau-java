package ru.spbau.mayorov.task7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Deserializes object from given properties.
 * @author Arseny Mayorov.
 *         Date: 26.05.13
 */
public class ReflectionDeSerializer {

    /** Wraps primitive class.
     * @param c class to wrap
     * @return wrapper class
     * */
    private static <T> Class<T> wrap(Class<T> c) {
        return c.isPrimitive() ? (Class<T>) getWrappers().get(c) : c;
    }

    /** Initializes wrappers' collection. */
    private static Map<Class<?>, Class<?>> getWrappers() {
        HashMap<Class<?>, Class<?>> map = new HashMap<>();
        map.put(boolean.class, Boolean.class);
        map.put(byte.class, Byte.class);
        map.put(char.class, Character.class);
        map.put(double.class, Double.class);
        map.put(float.class, Float.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(short.class, Short.class);
        map.put(void.class, Void.class);
        return map;
    }

    /** Gets property's name for given method's name.
     * @param mName method's name
     * @return property's name
     */
    private static String methodNameToPropertyName(String mName) {
        return mName.substring(3, 4).toLowerCase() + mName.substring(4);
    }

    /** Deserializes object from given properties.
     *
     * @param properties properties object
     * @param obj instance of object to initialize
     * @param <T> class of deserialized object
     * @return deserialized object (== obj)
     */
    public static <T> T deserialize(Properties properties, T obj) {

        for (Method m: obj.getClass().getMethods()) {
            if (!m.getName().substring(0, 3).equals("set"))
                continue;

            String propName = methodNameToPropertyName(m.getName());
            String prop = properties.getProperty(propName);
            if (prop == null)
                continue;

            Class<?> paramClass = wrap(m.getParameterTypes()[0]);
            try {
                Constructor ctor = paramClass.getConstructor(String.class);
                m.invoke(obj, ctor.newInstance(prop));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                continue;
            } catch (InstantiationException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }


        }

        return obj;
    }

}
