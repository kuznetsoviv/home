package ru.kuznetsoviv.dynamic.method.factory;

import ru.kuznetsoviv.dynamic.method.api.Generator;

/**
 * Генератор паролей.
 */
public class PasswordGenerator {

    private String name;

    /**
     * Сгенерировать пароль.
     */
    @Generator
    public String generatePassword() {
        return "New password for " + name;
    }

}
