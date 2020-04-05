package ru.kuznetsoviv.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

/**
 * Пример использования URLClassLoader - может использоваться для динамической погрузки классов.
 * В данном случае я указываю папку, откуда нужно брать необходимые ресурсы.
 * В jvm отсутствует механизм удаления classLoader, поэтому при деплое/раздеплое модулей может происходить утечка ресурсов.
 */
public class URLClassLoaderExample {

    public static void main(String[] args) throws Exception {
        URL url = URLClassLoaderExample.class.getResource("/ru/kuznetsoviv/classloader/SimpleBean.class");
        URL classLoaderURL = Paths.get(url.toURI()).getParent().toUri().toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{classLoaderURL});
        Class<?> clazz = Class.forName("ru.kuznetsoviv.classloader.SimpleBean", true, urlClassLoader);
        Method method = clazz.getMethod("doSomething", String.class);
        Object object = clazz.getDeclaredConstructor().newInstance();
        method.invoke(object, "reflection");
    }

}
