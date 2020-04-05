package ru.kuznetsoviv.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Демонстрация BeanInfo.
 * BeanInfo позволяет извлеч гктторы и сетторы из бина.
 */
public class IntrospectorExample {

    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(IntrospectorBean.class);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor propertyDescriptorName = null;
        for (PropertyDescriptor descriptor : descriptors) {
            if (descriptor.getName().equals("name")) {
                propertyDescriptorName = descriptor;
            }
        }
        IntrospectorBean object = new IntrospectorBean("Ivan", "Ivanov");
        System.out.println(propertyDescriptorName.getReadMethod().invoke(object));
    }

}
