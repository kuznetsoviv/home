package ru.kuznetsoviv.introspector;

/**
 * Тестовый бин, используемый для демонстрации BeanInfo.
 */
public class IntrospectorBean {

    private String name;
    private String surName;

    public IntrospectorBean(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }
}
