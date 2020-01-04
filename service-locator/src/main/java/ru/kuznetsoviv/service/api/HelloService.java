package ru.kuznetsoviv.service.api;

import java.util.ServiceLoader;

/**
 * Интерфейс для использования сервисов.
 */
public interface HelloService {

    /**
     * Тестовый метод, переопределяемый в различных реализациях.
     */
    void sayHello();

    /**
     * Метод для опеределия того, какой сервис нужно использовать.
     *
     * @return имя используемого сервиса
     */
    String getName();

    /**
     * Получить экземпляр сервиса по имени.
     *
     * @param name имя сервиса
     * @return экземпляр сервиса
     */
    static HelloService getInstance(String name) {
        ServiceLoader<HelloService> helloServices = ServiceLoader.load(HelloService.class);

        helloServices.iterator();
        for (HelloService helloService : helloServices) {
            if (name.equals(helloService.getName())) {
                return helloService;
            }
        }
        throw new RuntimeException("No such implementations: " + name);
    }

}
