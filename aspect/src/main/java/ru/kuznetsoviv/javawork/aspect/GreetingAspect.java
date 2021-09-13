package ru.kuznetsoviv.javawork.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Пример использования аспекта.
 * Сам класс - aspect.
 * Перечисление в pointcut - joint point
 * Метод beforeAdvice - advice
 */
@Aspect
public class GreetingAspect {

    @Pointcut("execution(* Main.printName(String))")
    public void greeting() {
        //
    }

    @Before("greeting()")
    public void beforeAdvice() {
        System.out.print("Hello ");
    }
}
