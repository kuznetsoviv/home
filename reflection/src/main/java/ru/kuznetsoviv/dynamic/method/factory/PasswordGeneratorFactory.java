package ru.kuznetsoviv.dynamic.method.factory;

import ru.kuznetsoviv.dynamic.method.api.Generator;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Фабрика генераторов паролей. Использует reflection для создания экземпляра класса, поиска его метода по аннотации
 * и вызов этого метода.
 */
public class PasswordGeneratorFactory {

    /**
     * Получить пароль.
     *
     * @return пароль
     */
    public static String getPassword() {
        try {
            Class clazz = Class.forName(getGenerator());
            Object object = createObject(clazz);
            Method method = null;
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                if (declaredMethod.getAnnotation(Generator.class) != null) {
                    method = declaredMethod;
                }
            }
            if (method != null) {
                return (String) method.invoke(object);
            }
            return "";
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getGenerator() throws IOException {
        Properties properties = new Properties();
        String filePath = PasswordGeneratorFactory.class.getResource("/generator.properties").getFile();
        properties.load(new FileReader(filePath));
        return properties.getProperty("generator.class");
    }

    @SuppressWarnings("unchecked")
    private static Object createObject(Class clazz) throws Exception {
        Object object = clazz.getDeclaredConstructor().newInstance();
        return fillProperties(object);
    }

    private static Object fillProperties(Object object) throws Exception {
        Properties properties = new Properties();
        String filePath = PasswordGeneratorFactory.class.getResource("/generator.properties").getFile();
        properties.load(new FileReader(filePath));
        for (String propertyName : properties.stringPropertyNames()) {
            if (!"generator.class".equals(propertyName)) {
                String value = properties.getProperty(propertyName);
                System.out.println("Property: " + propertyName + " = " + propertyName);
                Field field = object.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(object, value);
            }
        }
        return object;
    }

}
